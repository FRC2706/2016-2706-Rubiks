package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithCamera;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WaitThenRotateDriveWithCamera extends CommandGroup {
	
	public WaitThenRotateDriveWithCamera(double speed, long time) {
		this.addSequential(new StraightDriveWithTime(0.0, time));
		this.addSequential(new RotateDriveWithCamera(speed, 100));
	}
}
