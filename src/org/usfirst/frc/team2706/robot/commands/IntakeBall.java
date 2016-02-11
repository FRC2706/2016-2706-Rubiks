package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command {

	private final double speed;
	
	/*
	 * Make sure that the motors are turning in
	 */
	public IntakeBall(double speed) {
		super();
		
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.platformMotors.pickupBall(speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
