package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/*
 * Command that when pressed shoots the ball outwards by first,
 * turning the motors on, and then after 200ms using the pneumatics
 * to shoot the ball out.
 */
public class ShootBall extends Command {

	// get the time for when to shoot the ball after motors are at full speed
	float time;
	private double speed;
	
	// automatically sets the speed of the motors to full
	public ShootBall() {
		super();
		requires(Robot.shootIntakeMechanism);
		
		this.speed = 1;
	}
	
	// lets the user decide what speed the motors are at
	public ShootBall(double speed) {
		super();
		requires(Robot.shootIntakeMechanism);
		
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		time = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.shootIntakeMechanism.platformMotors(speed);
		
		if(time + 200 < System.currentTimeMillis()) {
			Robot.shootIntakeMechanism.platformPneumatics(true);
		}
	}

	@Override
	protected boolean isFinished() {
		if(time + 750 < System.currentTimeMillis())
			return true;
		return false;
	}

	@Override
	protected void end() {
		Robot.shootIntakeMechanism.platformMotors(0);
		Robot.shootIntakeMechanism.platformPneumatics(false);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
