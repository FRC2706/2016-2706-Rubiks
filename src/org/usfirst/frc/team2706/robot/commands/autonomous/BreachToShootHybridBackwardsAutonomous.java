package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ShootBall;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BackwardsBreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetBackwardsHybridPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachToShootHybridBackwardsAutonomous extends CommandGroup {
	
	public BreachToShootHybridBackwardsAutonomous() {
		
		this.addSequential(new StraightDriveWithEncoders(-0.4,-2,25));
		this.addSequential(new BackwardsBreachPlay());
		this.addSequential(new GoToTargetBackwardsHybridPlay());
		this.addSequential(new ShootBall(TeleopPneumaticControl.SHOOT_SPEED));
	}
}
