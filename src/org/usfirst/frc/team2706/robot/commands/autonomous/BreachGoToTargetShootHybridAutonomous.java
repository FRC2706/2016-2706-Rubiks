package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetHybridPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootHybridAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootHybridAutonomous() {
		this.addSequential(new BreachPlay());
		this.addSequential(new GoToTargetHybridPlay());
		// TODO: Shoot!
	}
}
