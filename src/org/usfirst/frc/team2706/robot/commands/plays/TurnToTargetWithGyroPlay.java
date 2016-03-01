package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithCamera;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnToTargetWithGyroPlay extends CommandGroup {
	
	public TurnToTargetWithGyroPlay() {
		this.addSequential(new RotateDriveWithCamera(0.85, 100));
	}
}
