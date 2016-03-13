package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PortCullisBreachPlay extends CommandGroup {
public PortCullisBreachPlay() {
	this.addSequential(new StraightDriveWithEncoders(0.5,10,25));
	this.addParallel(new ArmUpAuto(),2);
	this.addSequential(new StraightDriveWithEncoders(0.5,15,25));
	
}
}
