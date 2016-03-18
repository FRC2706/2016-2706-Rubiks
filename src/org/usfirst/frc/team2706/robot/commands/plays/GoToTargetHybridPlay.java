package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.FloatControlAuto;
import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetHybridPlay extends CommandGroup {
	
	public GoToTargetHybridPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new StraightDriveWithTime(0.0, 500));
		this.addSequential(new QuickRotate(60));
		this.addSequential(new ArmUpAuto(),1);
		this.addSequential(new StraightDriveWithEncoders(0.4, 6.0, 25));
		this.addSequential(new ArmDownAuto(),1);
		this.addSequential(new FloatControlAuto(true),1);
	}
}
