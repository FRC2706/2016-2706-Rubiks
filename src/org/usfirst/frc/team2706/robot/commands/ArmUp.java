package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ArmUp extends Command {

	@Override
	protected void initialize() {
		Robot.armCylinder2.set(DoubleSolenoid.Value.kForward);
		Robot.armCylinder1.set(DoubleSolenoid.Value.kReverse);
		
	}

	@Override
	protected void execute() {
this.cancel();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
