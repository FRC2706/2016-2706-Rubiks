package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithCamera;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetCameraPlay extends CommandGroup {
	
	public GoToTargetCameraPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new StraightDriveWithTime(0.0, 1000));
		this.addSequential(new RotateDriveWithCamera(0.5, 25));
		this.addSequential(new StraightDriveWithEncoders(0.25, 1.0, 25));
	}
}
