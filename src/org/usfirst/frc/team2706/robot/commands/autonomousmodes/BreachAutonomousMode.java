package org.usfirst.frc.team2706.robot.commands.autonomousmodes;

import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachAutonomousMode extends CommandGroup {
	
	public BreachAutonomousMode() {
		this.addSequential(new StraightDriveWithEncoders(1, 5, 100));
	}
}
