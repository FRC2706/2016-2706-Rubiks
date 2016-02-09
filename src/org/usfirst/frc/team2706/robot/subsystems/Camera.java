package org.usfirst.frc.team2706.robot.subsystems;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.usfirst.frc.team2706.robot.RobotMap;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Camera extends Subsystem {
	public static final String CAMERA_IP = "10.27.6.231";
	public static final float DEFAULT_PAN = 0.5f;
	public static final float DEFAULT_TILT = 1;
	public Servo panServo; 
	public boolean PRINT_STUFF = false;
	public Servo tiltServo;
	public  String RPi_addr;
	public final  int visionDataPort = 1182;
public Camera(String ip) {
	super();
	
	RPi_addr = ip;
	panServo = new Servo(RobotMap.MOTOR_CAMERA_PAN);
	tiltServo = new Servo(RobotMap.MOTOR_CAMERA_TILT);
}
public class TargetObject {
	  public float boundingArea = -1;     // % of cam [0, 1.0]
	  //center of target
	  public float ctrX = -1;             // [-1.0, 1.0]
	  public float ctrY = -1;             // [-1.0, 1.0]
	  // the aspect ratio of the target we found. This can be used directly as a poor-man's measure of skew.
	  public float aspectRatio = -1;

		public String toString() {
			return "position: (" + ctrX + ", " + ctrY + "), boundingArea: " + boundingArea + ", aspectRatio: " + aspectRatio;
		}
}
	
	@SuppressWarnings("deprecation")
	public  ArrayList<TargetObject> getVisionData() {
			ArrayList<TargetObject> prList = new ArrayList<>();

			if(PRINT_STUFF)
				System.out.println("Setting up Sockets");

			try (
				Socket sock = new Socket(RPi_addr, visionDataPort);

				PrintWriter outToServer = new PrintWriter(sock.getOutputStream(), true);

				// BufferedReader inFromServer = new BufferedReader( new InputStreamReader(sock.getInputStream()));
			) {
				if(PRINT_STUFF)
					System.out.println("Sending request to TrackerBox2 for vision data");
				outToServer.println(""); // basically send an empty message
				outToServer.flush();

				byte[] rawBytes = new byte[2048];
				try {
					// rawData = inFromServer.read();
					if( sock.getInputStream().read(rawBytes) < 0 ) {
						System.out.println("Something went wrong reading response from TrackerBox2");
						return null;
					}

					String rawData = new String(rawBytes);
					if(PRINT_STUFF)
						System.out.println("I got back: " + rawData);

					if(rawData.length() == 0) {
						prList.add(new TargetObject());
					}
					String[] targets = rawData.split(":");
					for(String target : targets) {
						String[] targetData = target.split(",");

						TargetObject pr = new TargetObject();
						pr.ctrX = Float.parseFloat(targetData[0]);
						pr.ctrY	= Float.parseFloat(targetData[1]);
						pr.aspectRatio = Float.parseFloat(targetData[2]);
						pr.boundingArea = Float.parseFloat(targetData[3]);

						if(PRINT_STUFF)
							System.out.println("Target found at: " + pr.ctrX + "," + pr.ctrY + ", and aspectRatio and boundingArea is: " + pr.aspectRatio + "," + pr.boundingArea);

						prList.add(pr);
					}

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



	public  TargetObject getVisionDataByTarget(int target) { // put in -1 for center target, -2 for right and -3 for left
		ArrayList<TargetObject> pr = getVisionData();
		System.out.println(pr.get(0));
		if (target == -1) {
			if (pr.size() % 2 == 0)
				return pr.get(pr.size() / 2);
			else
				return pr.get((int) (pr.size() / 2 + 0.5));
		} else if (target == -2)
			return pr.get(pr.size() - 1);
		else if (target == -3)
			return pr.get(0);
		else
			return pr.get(target);
	}
	public  ArrayList<TargetObject> getVisionDataArray(int target) {
		return getVisionData();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void SetServoAngles(float panAngle, float tiltAngle) {
		// pan and tilt can be from -1 to 1, servos can only be 0 and 1
		
		//this code checks if it is all good on the camera side
		if(panAngle <= 0 && panAngle >= -0.05 || panAngle >= 0 && panAngle <= 0.05) 
			return;
		//Pan servo movement
		if(panAngle < 0) {
			panServo.set(-panAngle / 2);
		}
		else if(panAngle > 0) {
			panServo.set((panAngle / 2) + 0.5);
		}
		//Tilt servo movement
		if(tiltAngle < 0) {
			panServo.set(-tiltAngle / 2);
		}
		else if(tiltAngle > 0) {
			panServo.set((tiltAngle / 2) + 0.5);
		}
	}
}
