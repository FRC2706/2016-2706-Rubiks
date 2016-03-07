package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachPlay extends CommandGroup {
	
	public BreachPlay() {
		this.addSequential(new ArmDownAuto());
		//this.addSequential(new StraightDriveWithTime(0.0,1000));
		this.addSequential(new StraightDriveWithEncoders(0.4,19, 25));
	}
}
