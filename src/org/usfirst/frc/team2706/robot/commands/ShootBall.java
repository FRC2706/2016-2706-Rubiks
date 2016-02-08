package org.usfirst.frc.team2706.robot.commands;


import java.util.TimerTask;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {

	
	private final double speed;
	
	public ShootBall(double speed) {
		super();
		
		
		// requires, etc. code
		this.speed = speed;
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.motors.shoot(speed);
	}
	
	protected boolean isFinished() {
		// @TODO return whether or not the ball has been shot out
		return false;
	}

	

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
}
