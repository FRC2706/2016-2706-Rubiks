package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetGyroPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootGyroAutonomous extends CommandGroup {
	
	public BreachGoToTargetShootGyroAutonomous() {
		this.addSequential(new BreachPlay());
		this.addSequential(new GoToTargetGyroPlay());
		// TODO: Shoot!		
	}
}
