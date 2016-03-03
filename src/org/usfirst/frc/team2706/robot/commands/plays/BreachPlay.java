package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachPlay extends CommandGroup {
	
	public BreachPlay() {
		this.addSequential(new StraightDriveWithEncoders(0.85, 15, 100));
	}
}
