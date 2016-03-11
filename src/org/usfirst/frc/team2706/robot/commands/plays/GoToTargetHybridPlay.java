package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithHybrid;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetHybridPlay extends CommandGroup {
	
	public GoToTargetHybridPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new StraightDriveWithTime(0.0, 1000));
		this.addSequential(new RotateDriveWithHybrid(0.6, 25));
		this.addSequential(new StraightDriveWithEncoders(0.3, 6.0, 25));
	}
}
