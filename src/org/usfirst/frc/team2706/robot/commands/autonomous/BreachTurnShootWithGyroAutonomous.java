package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.TurnToTargetWithGyroPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachTurnShootWithGyroAutonomous extends CommandGroup {
	
	public BreachTurnShootWithGyroAutonomous() {
		this.addSequential(new BreachPlay());
		this.addSequential(new TurnToTargetWithGyroPlay());
		// TODO: Shoot!		
	}
}
