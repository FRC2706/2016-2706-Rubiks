package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetBackwardsHybridPlay extends CommandGroup {

    public GoToTargetBackwardsHybridPlay() {
        // TODO: Fix speeds and distances
        this.addSequential(new StraightDriveWithTime(0.0, 500));
        this.addSequential(new QuickRotate(180));
        this.addSequential(new StraightDriveWithTime(0.0, 1000));
        this.addSequential(new QuickRotate(50));
        this.addSequential(new StraightDriveWithEncoders(0.4, 6, 25));
    }
}
