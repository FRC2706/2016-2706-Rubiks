package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Command that when run, constantly turns the motors outward,
 * until interrupted, for passing purposes
 */
public class PassBall extends Command {

	double speed;
	
	
	public PassBall(double speed) {
		super();
		requires(Robot.shootIntakeMechanism);
		
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.shootIntakeMechanism.platformMotors(speed);
	}

	@Override
	protected boolean isFinished() {
		// program never ends while the button is held
		return false;
	}

	@Override
	protected void end() {
		Robot.shootIntakeMechanism.platformMotors(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
