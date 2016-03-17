package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChevalDeFriseBreachPlay extends CommandGroup {
public ChevalDeFriseBreachPlay() {
	this.addSequential(new StraightDriveWithTime(0.4,3000));
	this.addSequential(new ArmDownAuto(),1);
	this.addSequential(new StraightDriveWithTime(0.8,3000));
}
}
