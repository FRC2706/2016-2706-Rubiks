package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.FloatControlAuto;
import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetGyroPlay extends CommandGroup {
	
	public GoToTargetGyroPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new StraightDriveWithTime(0,200));
		this.addSequential(new QuickRotate(60));
		this.addSequential(new StraightDriveWithTime(0,200));
		this.addParallel(new ArmUpAuto(), 0.5);
		this.addSequential(new StraightDriveWithEncoders(0.6, 9, 10));
		this.addSequential(new ArmDownAuto(), 0.5);
		this.addSequential(new FloatControlAuto(true), 0.5);
	}
}
