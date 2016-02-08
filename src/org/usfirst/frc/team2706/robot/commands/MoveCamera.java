package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCamera extends Command {
	float boat; // must be a float or else it sinks
	public static final int TARGET = -1;
	private float cachedLocationX = Camera.DEFAULT_PAN;
	private float cachedLocationY = Camera.DEFAULT_TILT;
	private Camera.TargetObject target;
	public MoveCamera() {
	     requires(Robot.camera);
	}
	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
				target = Robot.camera.getVisionDataByTarget(TARGET);
				cachedLocationX =  target.ctrX;
				cachedLocationY =  target.ctrY;
				System.out.println("Network call finished, current location is: " + cachedLocationX + "," + cachedLocationY);
				} catch(NullPointerException e) {
					System.out.println("Data retrieval failed, resorting to last known values");
				}
			}
			
		}).start();

		Robot.camera.SetServoAngles(cachedLocationX,cachedLocationY);
	}

	@Override
	protected void initialize() {
System.out.println("Camera Initialized");
	}

	@Override
	protected void interrupted() {
		end();

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
