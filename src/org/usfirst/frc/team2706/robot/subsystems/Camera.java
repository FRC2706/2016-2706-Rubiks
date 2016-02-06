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
	public static final String CAMERA_IP = "192.168.1.10";
	public Servo panServo; 
	public Servo tiltServo;
	public  String RPi_addr;
	public final int changeProfilePort = 1181;
	public final  int getVisionDataPort = 1182;
public Camera(String ip) {
	RPi_addr = ip;
	panServo = new Servo(RobotMap.MOTOR_CAMERA_PAN);
	tiltServo = new Servo(RobotMap.MOTOR_CAMERA_TILT);
}
	public class TargetObject {
		  public float boundingArea;     // % of cam [0, 1.0]
		  //center of target
		  public float ctrX;             // [-1.0, 1.0]
		  public float ctrY;             // [-1.0, 1.0]
		  // the aspect ratio of the target we found. This can be used directly as a poor-man's measure of skew.
		  public float aspectRatio;
		public String toString() {
			return ctrX + "," + ctrY + "," + boundingArea + "," + aspectRatio;
		}
	}
	
	@SuppressWarnings("deprecation")
	public  ArrayList<TargetObject> getVisionData() {
		ArrayList<TargetObject> prList = new ArrayList<>(); 
		try{
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
				String[] targets = rawData.split(":");
				for(String target : targets) {
					TargetObject pr = new TargetObject();
					String[] targetData = rawData.split(",");
					pr.ctrX = Float.parseFloat(targetData[0]);
					pr.ctrY	= Float.parseFloat(targetData[1]);
					pr.boundingArea = Float.parseFloat(targetData[2]);
					pr.aspectRatio = Float.parseFloat(targetData[3]);
					prList.add(pr);
				}

//				System.out.println("ParticleReport:\n" + pr);
			} catch (java.io.EOFException e) {
				System.out.println("TrackerBox2: Communication Error");
			}
			
			sock.close();
		} catch ( UnknownHostException e) {
			System.out.println("Host unknown: "+RPi_addr);
			return null;
		} catch (java.net.ConnectException e) {
			System.out.println("TrackerBox Raspberry Pi is either not connected, or is not at address " + RPi_addr);
			return null;
		} catch( IOException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public void SetServoAngles(int panAngle, int tiltAngle) {
		panServo.set(panAngle);
		tiltServo.set(tiltAngle);
	}
}
