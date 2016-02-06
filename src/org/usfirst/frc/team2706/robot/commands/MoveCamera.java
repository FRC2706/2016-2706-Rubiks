package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCamera extends Command {
	float boat; // must be a float or else it sinks
	public static final int TARGET = -1;
	private int cachedLocationX = 0;
	private int cachedLocationY = 0;
	private Camera.TargetObject target;

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		target = Robot.camera.getVisionDataByTarget(TARGET);
		cachedLocationX = (int) target.ctrX;
		cachedLocationY = (int) target.ctrY;
		Robot.camera.SetServoAngles(cachedLocationX,cachedLocationY);
	}

	@Override
	protected void initialize() {

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
