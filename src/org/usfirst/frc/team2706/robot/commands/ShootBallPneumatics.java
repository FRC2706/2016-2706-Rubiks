package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/*
 * Once the motors are turned on, then one can press the shoot
 * button to punch the ball out of the robot
 */
public class ShootBallPneumatics extends Command {

	float time;
	
	public ShootBallPneumatics() {
		super();
		
	}
	
	@Override
	protected void initialize() {
		// take the current time and run the method for 500 milliseconds
		time = System.currentTimeMillis();
		
		
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		if(time + 500 < System.currentTimeMillis())
			return true;
		
		return false;
	}

	@Override
	protected void end() {
		// turns the pneumatics off
		Robot.pneumaticShoot.endPneumatic();
		
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
