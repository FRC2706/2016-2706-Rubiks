package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.FloatControlAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachPlay extends CommandGroup {
	
	public BreachPlay() {
		this.addSequential(new ArmDownAuto(),2);
		this.addSequential(new FloatControlAuto(true),1);
		//this.addSequential(new StraightDriveWithTime(0.0,1000));
		this.addSequential(new StraightDriveWithEncoders(0.4,5.5, 25));
	}
}
