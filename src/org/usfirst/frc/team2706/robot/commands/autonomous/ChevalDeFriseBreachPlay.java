package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChevalDeFriseBreachPlay extends CommandGroup {
public ChevalDeFriseBreachPlay() {
//	this.addSequential(new StraightDriveWithTime(0.65,2000));
	this.addSequential(new StraightDriveWithEncoders(0.6, 5.5, 25));
	this.addParallel(new StraightDriveWithEncoders(0.5, 0, 63));
	this.addSequential(new ArmDownAuto(),1);
	this.addSequential(new StraightDriveWithTime(1.0,1500));
}
}
