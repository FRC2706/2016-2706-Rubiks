package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Command that changes the position of the platform that holds and shoots
 * the ball
 * if user enters true the platform goes down, if false the platform goes up
 */
public class ChangePlatformPosition extends Command {

	float time;
	boolean position;
	
	public ChangePlatformPosition(boolean position) {
		super();
		
		this.position = position;
	}
	
	@Override
	protected void initialize() {
		time = System.currentTimeMillis();
	}

	
	
	@Override
	protected void execute() {
		Robot.platform.changePosition(position);
	}

	// the program finishes after 1 second (1000 milliseconds)
	@Override
	protected boolean isFinished() {
		if(time + 1000 < System.currentTimeMillis())
			return true;
		
		return false;
	}

	@Override
	protected void end() {
		// TODO figure out if anything is needed to end the command
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
