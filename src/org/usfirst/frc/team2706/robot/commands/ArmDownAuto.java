package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ArmDownAuto extends Command {

	@Override
	protected void initialize() {

		
	}

	@Override
	protected void execute() {
		Robot.armCylinder2.set(DoubleSolenoid.Value.kForward);
		Robot.armCylinder1.set(DoubleSolenoid.Value.kForward);
	}
int ticks = 0;
	@Override
	protected boolean isFinished() {
		if (++ticks < 40)
			return false;
		else {
			ticks = 0;
			return true;
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void end() {
		Robot.armCylinder1.set(DoubleSolenoid.Value.kOff);
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
