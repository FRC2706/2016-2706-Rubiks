package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetCameraPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootCameraAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootCameraAutonomous() {
		this.addSequential(new BreachPlay());
		this.addSequential(new GoToTargetCameraPlay());
		// TODO: Shoot!
	}
}
