package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmUpBreachPlay extends CommandGroup {
public ArmUpBreachPlay() {
	//this.addSequential(new ArmUpAuto());
	//this.addSequential(new StraightDriveWithTime(0.0,2000));
this.addSequential(new StraightDriveWithEncoders(0.6,15, 25));
	}
}