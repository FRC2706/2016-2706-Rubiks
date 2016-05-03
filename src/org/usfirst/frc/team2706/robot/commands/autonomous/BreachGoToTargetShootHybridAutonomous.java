package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ShootBall;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.plays.BreachPlay;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetHybridPlay;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachGoToTargetShootHybridAutonomous extends CommandGroup {

    public BreachGoToTargetShootHybridAutonomous() {
        this.addSequential(new BreachPlay());
        this.addSequential(new GoToTargetHybridPlay());
        this.addSequential(new ShootBall(TeleopPneumaticControl.SHOOT_SPEED));
    }
}
