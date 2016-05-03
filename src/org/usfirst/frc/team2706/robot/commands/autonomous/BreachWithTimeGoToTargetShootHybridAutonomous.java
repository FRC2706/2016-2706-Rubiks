package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDown;
import org.usfirst.frc.team2706.robot.commands.RotateDriveWithHybrid;
import org.usfirst.frc.team2706.robot.commands.ShootBall;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachWithTimeGoToTargetShootHybridAutonomous extends CommandGroup {
    public BreachWithTimeGoToTargetShootHybridAutonomous() {
        this.addSequential(new ArmDown());
        this.addSequential(new StraightDriveWithTime(0.0, 1000));
        this.addSequential(new StraightDriveWithTime(0.85, 3000));
        this.addSequential(new StraightDriveWithTime(0.0, 1000));
        this.addSequential(new RotateDriveWithHybrid(0.6, 25));
        this.addSequential(new StraightDriveWithTime(0.75, 750));
        this.addSequential(new ShootBall(TeleopPneumaticControl.SHOOT_SPEED));
    }
}
