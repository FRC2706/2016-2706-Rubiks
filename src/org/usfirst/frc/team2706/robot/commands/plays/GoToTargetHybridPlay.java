package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.FloatControlAuto;
import org.usfirst.frc.team2706.robot.commands.QuickRotateWithCamera;
import org.usfirst.frc.team2706.robot.commands.QuickViewWithCamera;
import org.usfirst.frc.team2706.robot.commands.ResetCameraEndAuto;
import org.usfirst.frc.team2706.robot.commands.RotateDriveWithHybrid;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetHybridPlay extends CommandGroup {
	
	public double cameraAngle = 0;
	
	public GoToTargetHybridPlay() {
		// TODO: Fix speeds and distances
		this.addSequential(new StraightDriveWithTime(0.0, 2000));
		this.addSequential(new QuickViewWithCamera(this));
		this.addSequential(new StraightDriveWithEncoders(0.4, 10.0/12.0, 25));
		this.addSequential(new QuickRotateWithCamera(this));
		this.addSequential(new StraightDriveWithEncoders(0.5, 2, 25));
		this.addSequential(new ResetCameraEndAuto());
		this.addSequential(new ArmUpAuto(),1);
		this.addSequential(new StraightDriveWithEncoders(0.5, 4.5, 25));
		this.addSequential(new ArmDownAuto(),1);
		this.addSequential(new FloatControlAuto(true),1);
	}
}
