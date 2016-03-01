package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnToTargetWithCameraPlay extends CommandGroup {
	
	public TurnToTargetWithCameraPlay() {
		this.addSequential(new RotateDriveWithGyro(0.85, 45, 100));
	}
}
