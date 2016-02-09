package org.usfirst.frc.team2706.robot.commands;

// @TODO should program run on a timer
import java.util.TimerTask;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBallMotors extends Command {

	private double speed;
	
	public ShootBallMotors(double speed) {
		super();
		
		// requires, etc. code
		this.speed = speed;
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.platformMotors.shoot(speed);
		

	}
	
	protected boolean isFinished() {
		// @TODO return whether or not the ball has been shot out
		return false;
	}

	

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	@Override
	protected void end() {
		// shut off motors
		Robot.platformMotors.shoot(0);
		
		// @TODO does the pneumatic thing have to be reset?
	}
}
