package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ShootBallAuto;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BreachPlayGyro;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetGyroPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootGyroAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootGyroAutonomous() {
		this.addSequential(new BreachPlayGyro());
		this.addSequential(new GoToTargetGyroPlay());		
		//this.addParallel(new StraightDriveWithTime(0.6, 3000));
		this.addSequential(new ShootBallAuto(TeleopPneumaticControl.SHOOT_SPEED));		
	}
}
