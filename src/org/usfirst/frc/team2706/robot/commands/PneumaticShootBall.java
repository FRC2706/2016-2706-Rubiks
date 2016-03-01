package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PneumaticShootBall extends Command {

	float time;
	
	public PneumaticShootBall() {
		super();
		requires(Robot.pneumaticMechanism);
		
	}
	
	@Override
	protected void initialize() {
		time = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.pneumaticMechanism.platformPneumatics(true);
	}

	@Override
	protected boolean isFinished() {
		if(time + 200 < System.currentTimeMillis())
			return true;
		return false;
	}

	@Override
	protected void end() {
		// TODO make sure this code reverses the shooter
		Robot.pneumaticMechanism.platformPneumatics(false);
		Robot.pneumaticMechanism.platformPneumaticsEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
