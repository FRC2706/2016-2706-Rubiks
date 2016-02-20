package org.usfirst.frc.team2706.robot.subsystems;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.RobotMap;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Camera extends Subsystem {
	public static final String CAMERA_IP = "10.27.6.240";
	public static final float DEFAULT_PAN = 0.5f;
	public static final float DEFAULT_TILT = 1;
	public static final float SEARCH_SPEED = 0.01f;
	public static final float SEARCH_TILT_BOTTOM = 0.9f;
	public static final float SEARCH_TILT_TOP = 0.4f;
	public static final float SEARCH_TILT_INCREMENT = 0.1f;
	public static final float SEARCH_PAN_RIGHT = 1;
	public static final float SEARCH_PAN_LEFT = 0;
	public boolean PRINT_STUFF = true;
	public Servo turnXAxis; 
	public Servo turnYAxis;
	public  String RPi_addr;
	public final  int visionDataPort = 1182;
	public static TargetObject cachedTarget;
public Camera(String ip) {
	super();
	
	RPi_addr = ip;
	turnXAxis = new Servo(RobotMap.MOTOR_CAMERA_PAN);
	turnYAxis = new Servo(RobotMap.MOTOR_CAMERA_TILT);
}
public class TargetObject implements Comparable<TargetObject> {
	  public float boundingArea = -1;     // % of cam [0, 1.0]
	  //center of target
	  public float ctrX = -1;             // [-1.0, 1.0]
	  public float ctrY = -1;             // [-1.0, 1.0]
	  // the aspect ratio of the target we found. This can be used directly as a poor-man's measure of skew.
	  public float aspectRatio = -1;
	  
		public String toString() {
			return "position: (" + ctrX + ", " + ctrY + "), boundingArea: " + boundingArea + ", aspectRatio: " + aspectRatio;
		}

		@Override
		public int compareTo(TargetObject other) {
			if (this.ctrX - other.ctrX < 0)
				return -1;
			else if (this.ctrX - other.ctrX > 0)
				return 1;
			else 
				return 0;
			
		}
}
	
//@SuppressWarnings("deprecation")
	public ArrayList<TargetObject> getVisionData() {
		ArrayList<TargetObject> prList = new ArrayList<>();

		if(PRINT_STUFF)
			System.out.println("Setting up Sockets");

		Socket sock = new Socket();
		try {
			sock.setSoTimeout(5);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		try {
			sock.connect(new InetSocketAddress(RPi_addr, visionDataPort), 20);
		} catch (Exception e) {
			return null;
		}

		try {
			PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);

			if(PRINT_STUFF)
				System.out.println("Sending request to TrackerBox2 for vision data");
			outToServer.println(""); // basically send an empty message
			outToServer.flush();

			byte[] rawBytes = new byte[2048];
			try {
				// rawData = inFromServer.read();
				int n;
				if( sock.getInputStream().read(rawBytes) < 0 ) {
					System.out.println("Something went wrong reading response from TrackerBox2: ");
					return null;
				}

				String rawData = new String(rawBytes);
				if(PRINT_STUFF)
					System.out.println("I got back: " + rawData);

//				if(rawData.length() == 1) {
////					prList.add(new TargetObject());
//					System.out.println("No Targets Found");
//					return prList;
//				} else {
					String[] targets = rawData.split(":");
					for(String target : targets) {
						try {
							String[] targetData = target.split(",");

							TargetObject pr = new TargetObject();
							pr.ctrX = Float.parseFloat(targetData[0]);
							pr.ctrY	= Float.parseFloat(targetData[1]);
							pr.aspectRatio = Float.parseFloat(targetData[2]);
							pr.boundingArea = Float.parseFloat(targetData[3]);

							if(PRINT_STUFF)
								System.out.println("Target found at: " + pr.ctrX + "," + pr.ctrY + ", and aspectRatio and boundingArea is: " + pr.aspectRatio + "," + pr.boundingArea);

							prList.add(pr);
						} catch (NumberFormatException e) {
							continue;
						}
					}
//				}
			} catch (java.io.EOFException e) {
				System.out.println("Camera: Communication Error");
			}

			sock.close();
		} catch ( UnknownHostException e) {
			System.out.println("Host unknown: "+RPi_addr);
			return null;
		} catch (java.net.ConnectException e) {
			System.out.println("Camera initialization failed at: " + RPi_addr);
			return null;
		} catch( IOException e) {
			e.printStackTrace();
			return null;
		}
		if(PRINT_STUFF)
			System.out.println("Network call successful, returning not null data...");

		return prList;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static final int LEFT_TARGET = -3;
	public static final int CENTER_TARGET = -1;
	public static final int RIGHT_TARGET = -2;
	
/*	public float savedXaxis = DEFAULT_PAN;
	public float savedYaxis = DEFAULT_TILT;*/
	public  TargetObject getVisionDataByTarget(int target) { // put in -1 for center target, -2 for right and -3 for left
		ArrayList<TargetObject> pr = getVisionData();
		Collections.sort((List<TargetObject>) pr);
		// pr should now be sorted
		
		if(pr.size() == 0) {
			if(PRINT_STUFF)
			System.out.println("Can't find a target");
			/*TargetObject to = new TargetObject();
			to.ctrX = (float) turnXAxis.getPosition();
			to.ctrY = (float) turnYAxis.getPosition();
			return to;
		}*/
			return null;
		}
		if (target == CENTER_TARGET) {
			if (pr.size() % 2 == 0)
				return pr.get(pr.size() / 2);
			else
				return pr.get((int) (pr.size() / 2 + 0.5));
		} else if (target == RIGHT_TARGET)
			return pr.get(pr.size() - 1);
		else if (target == LEFT_TARGET)
			return pr.get(0);
		else
			return pr.get(target);
	}
	public void SetServoAngles(float panAngle, float tiltAngle) {
		 boolean inDeadZoneX = false;
		 boolean inDeadZoneY = false;
		// pan and tilt can be from -1 to 1, servos can only be 0 and 1
		
		//this code checks if it is all good on the camera side
/*		if((panAngle <= 0.1) && (panAngle >=  -0.1)) {
			inDeadZoneX = true;
		}
		if((tiltAngle <= 0.1) && (tiltAngle >= -0.1 )) {
			inDeadZoneY = true;
		}*/
		//Pan servo movement
		if(!inDeadZoneX) {
			double newVal = (panAngle >= 0.0 ? panAngle * panAngle : -1 * panAngle * panAngle) / 7.5;
			System.out.println(newVal);
			turnXAxis.set(turnXAxis.getPosition() - newVal);
		}
		
/*		else if(panAngle > 0 && !inDeadZoneX) {
			turnXAxis.set(turnXAxis.getPosition() - 0.02);
		//}*/
		//Tilt servo movement
		if(!inDeadZoneY) {
			double newVal = (tiltAngle >= 0.0 ? tiltAngle * tiltAngle : -1 * tiltAngle * tiltAngle) / 7.5;
			System.out.println(newVal);
			
			turnYAxis.set(turnYAxis.getPosition() + newVal);
		}
	//	}
/*		else if(tiltAngle > 0 && !inDeadZoneY) {
			turnYAxis.set(turnYAxis.getPosition() + 0.02);
		}*/
	}
	public void RawSetServoAxis(float panAxis, float tiltAxis) {
	turnXAxis.set(panAxis);
	turnYAxis.set(tiltAxis);
	}
	public float GetAspectRatio() {
		return cachedTarget.aspectRatio;
	}
	public float GetBoundingArea() {
		return cachedTarget.boundingArea;
	}
	public float GetCtrX() {
		return (float)turnXAxis.getPosition();
	}
	public float GetCtrY() {
		return (float)turnYAxis.getPosition();
	}
	private boolean turningRight;
	//Call this every tick to continue it
	public void FreeLook() {
		if(turningRight) {
		turnXAxis.set(turnXAxis.getPosition() + SEARCH_SPEED);
		}
		else {
			turnXAxis.set(turnXAxis.getPosition() - SEARCH_SPEED);
		}
		if(turnYAxis.getPosition() <= SEARCH_TILT_TOP) {
			turnYAxis.set(SEARCH_TILT_BOTTOM);
		}
		if(turnXAxis.getPosition() >= SEARCH_PAN_RIGHT) {
			turningRight = false;
			turnYAxis.set(turnYAxis.getPosition() - SEARCH_TILT_INCREMENT);
		}
		else if(turnXAxis.getPosition() <= SEARCH_PAN_LEFT) {
			turningRight = true;
			turnYAxis.set(turnYAxis.getPosition() - SEARCH_TILT_INCREMENT);
		}
	}
	public float RobotTurnDegrees() {
		return (float)turnXAxis.getPosition() / (1f / 180f) - 90f;
	}
}
