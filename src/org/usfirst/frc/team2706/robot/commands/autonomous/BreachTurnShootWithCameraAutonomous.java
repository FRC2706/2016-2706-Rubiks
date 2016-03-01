package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.TurnToTargetWithCameraPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachTurnShootWithCameraAutonomous extends CommandGroup {
	
	public BreachTurnShootWithCameraAutonomous() {
		this.addSequential(new BreachPlay());
		this.addSequential(new TurnToTargetWithCameraPlay());
		// TODO: Shoot!
	}
}
