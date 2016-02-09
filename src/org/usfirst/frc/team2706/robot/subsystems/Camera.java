package org.usfirst.frc.team2706.robot.subsystems;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	public Servo turnXAxis; 
	public Servo turnYAxis;
	public  String RPi_addr;
	public final int changeProfilePort = 1181;
	public final  int getVisionDataPort = 1182;
public Camera(String ip) {
	super();
	
	RPi_addr = ip;
	turnXAxis = new Servo(RobotMap.MOTOR_CAMERA_PAN);
	tiltServo = new Servo(RobotMap.MOTOR_CAMERA_TILT);
}
	public class TargetObject {
		  public float boundingArea = -1;     // % of cam [0, 1.0]
		  //center of target
		  public float ctrX = DEFAULT_PAN;             // [-1.0, 1.0]
		  public float ctrY = DEFAULT_TILT;             // [-1.0, 1.0]
		  // the aspect ratio of the target we found. This can be used directly as a poor-man's measure of skew.
		  public float aspectRatio = -1;
/*		public String toString() {
			return ctrX + "," + ctrY + "," + boundingArea + "," + aspectRatio;
		}*/
	}
	
	@SuppressWarnings("deprecation")
	public  ArrayList<TargetObject> getVisionData() {
		ArrayList<TargetObject> prList = new ArrayList<>(); 
		try{
			System.out.println("testing");
			Socket sock = new Socket(RPi_addr, getVisionDataPort);
			
			OutputStream outToServer = sock.getOutputStream();
			
			DataOutputStream out = new DataOutputStream(outToServer);
			
//			System.out.println("Sending request to TrackerBox2 for vision data");
			out.writeUTF( " " ); // basically send an empty message
			
			String rawData = "";
			DataInputStream in = new DataInputStream(sock.getInputStream());
			try {
				rawData = in.readLine();
//				System.out.println("I got back: " + rawData);
				if(rawData.length() == 0) {
					prList.add(new TargetObject());
				}
				String[] targets = rawData.split(":");
				for(String target : targets) {
					TargetObject pr = new TargetObject();
					String[] targetData = rawData.split(",");
					pr.ctrX = Float.parseFloat(targetData[0]);
					pr.ctrY	= Float.parseFloat(targetData[1]);
					pr.aspectRatio = Float.parseFloat(targetData[2]);
					pr.boundingArea = Float.parseFloat(targetData[3]);
					System.out.println("Network call finished, current location is: " + pr.ctrX + "," + pr.ctrY + ", and aspectRatio and boundingArea is: " + pr.aspectRatio + "," + pr.boundingArea);	
					prList.add(pr);
				}

//				System.out.println("ParticleReport:\n" + pr);
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
	public float savedXaxis = DEFAULT_PAN;
	public float savedYaxis = DEFAULT_TILT;
	public boolean inDeadZoneX = false;
	public boolean inDeadZoneY = false;
	public void SetServoAngles(float panAngle, float tiltAngle) {
		// pan and tilt can be from -1 to 1, servos can only be 0 and 1
		
		//this code checks if it is all good on the camera side
		if(panAngle <= DEFAULT_PAN && panAngle >= DEFAULT_PAN -0.05 || panAngle >= DEFAULT_PAN && panAngle <= DEFAULT_PAN + 0.05) {
			inDeadZoneX = true;
		}
		if(tiltAngle <= DEFAULT_TILT && tiltAngle >= DEFAULT_TILT -0.05 || tiltAngle >= DEFAULT_TILT && tiltAngle <= DEFAULT_TILT + 0.05) {
			inDeadZoneY = true;
		}
		//Pan servo movement
		if(panAngle < 0 && !inDeadZoneX) {
			turnXAxis.set((-panAngle / 2) + savedXaxis);
			savedXaxis = (-panAngle / 2) + savedXaxis;
		}
		else if(panAngle > 0 && !inDeadZoneX) {
			turnXAxis.set((panAngle / 2) + 0.25 + savedXaxis);
			savedXaxis = ((panAngle / 2) + 0.25f + savedXaxis);
		}
		//Tilt servo movement
		if(tiltAngle < 0 && !inDeadZoneY) {
			turnYAxis.set(-tiltAngle / 2 + savedYaxis);
			savedYaxis = (-tiltAngle / 2 + savedYaxis);
		}
		else if(tiltAngle > 0 && !inDeadZoneY) {
			turnYAxis.set((tiltAngle / 2) + 0.25 + savedYaxis);
			savedYaxis = ((tiltAngle / 2) + 0.25f + savedYaxis);
		

		}
	}
}
