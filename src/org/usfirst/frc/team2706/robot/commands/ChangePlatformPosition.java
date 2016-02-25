package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/*
 * Command that changes the position of the platform,
 * it takes an input of either true for down, and 
 * false for up.
 */
public class ChangePlatformPosition extends Command {

	// the program runs for 1 second to make sure everything is done
	float time;
	boolean position;
	
	
	public ChangePlatformPosition(boolean position) {
		super();
		requires(Robot.platformMechanism);
		
		this.position = position;
	}
	
	@Override
	protected void initialize() {
		time = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.platformMechanism.changePosition(position);
	}

	@Override
	protected boolean isFinished() {
		if(time + 1000 < System.currentTimeMillis())
			return true;
		return false;
	}

	@Override
	protected void end() {
		// TODO does there need to be any code here?
	}

	@Override
	protected void interrupted() {
		end();
	}

}
