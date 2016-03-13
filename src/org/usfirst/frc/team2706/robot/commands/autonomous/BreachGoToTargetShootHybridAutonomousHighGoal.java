package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.HighGoalShooter;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetHybridPlayHighGoal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootHybridAutonomousHighGoal extends CommandGroup {
	
	public BreachGoToTargetShootHybridAutonomousHighGoal() {
		this.addSequential(new BreachPlay());
		this.addSequential(new GoToTargetHybridPlayHighGoal());
		this.addSequential(new HighGoalShooter(TeleopPneumaticControl.SHOOT_SPEED));
	}
}
