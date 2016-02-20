package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCamera extends Command {
	float boat; // must be a float or else it sinks
	int count = 0;
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
		//Robot.camera.FreeLook();
		// TODO Auto-generated method stub

		try {
			target = Robot.camera.getVisionDataByTarget(TARGET);
			Camera.cachedTarget = target;
			if (target == null)
				return;
			if(cachedLocationX != target.ctrX && cachedLocationY != target.ctrY) {
			cachedLocationX =  target.ctrX;
			cachedLocationY =  target.ctrY;
			
			Robot.camera.SetServoAngles(cachedLocationX,cachedLocationY);
			System.out.println("Network call finished, current location is: " + cachedLocationX + "," + cachedLocationY);
			} 
		}catch(NullPointerException e) {
				System.out.println("Data retrieval failed, resorting to last known values");
			}
	
		}

	
		

	

	@Override
	protected void initialize() {
System.out.println("Camera Initialized");
Robot.camera.RawSetServoAxis(Camera.DEFAULT_PAN,Camera.DEFAULT_TILT);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
