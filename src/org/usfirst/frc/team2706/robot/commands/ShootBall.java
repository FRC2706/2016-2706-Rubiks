package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/*
 * Once the motors are turned on, then one can press the shoot
 * button to punch the ball out of the robot
 */
public class ShootBall extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// turns the pneumatics off
		Robot.platformMotors.endPneumaticPunch();
		
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
