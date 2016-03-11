package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDown;
import org.usfirst.frc.team2706.robot.commands.ShootBall;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetGyroPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootGyroAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootGyroAutonomous() {
		this.addSequential(new ArmDown());
		this.addSequential(new StraightDriveWithTime(0.0,1000));
		this.addSequential(new BreachPlay());
		this.addSequential(new GoToTargetGyroPlay());
		this.addSequential(new ShootBall(TeleopPneumaticControl.SHOOT_SPEED));		
	}
}
