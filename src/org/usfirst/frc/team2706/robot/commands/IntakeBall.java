package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/*
 * Command that when run constantly turns the motors, until
 * Interrupted, to intake a ball
 */
public class IntakeBall extends Command {

	// Sets the speed at which the motors turn
	double speed;
	
	public IntakeBall(double speed) {
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
		return false;
	}

	@Override
	protected void end() {
		// reset the motor speed to 0
		Robot.shootIntakeMechanism.platformMotors(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
