package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.RotateDriveWithGyro;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetGyroPlay extends CommandGroup {
	
	public GoToTargetGyroPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new QuickRotate(45));
		this.addSequential(new StraightDriveWithEncoders(0.25, 1.0, 25));
	}
}
