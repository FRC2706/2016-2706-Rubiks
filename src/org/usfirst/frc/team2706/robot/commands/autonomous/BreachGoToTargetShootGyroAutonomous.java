package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.FloatControlAuto;
import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.ShootBallAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetGyroPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootGyroAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootGyroAutonomous() {
		this.addSequential(new ArmDownAuto());
		this.addSequential(new FloatControlAuto(true),1);
		this.addSequential(new StraightDriveWithEncoders(0.4,10, 25));
		this.addSequential(new QuickRotate(60));
		this.addSequential(new StraightDriveWithEncoders(0.25, 2.0, 25));		
		this.addSequential(new ShootBallAuto(TeleopPneumaticControl.SHOOT_SPEED));		
	}
}
