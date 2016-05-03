package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.GetBallAuto;
import org.usfirst.frc.team2706.robot.commands.QuickRotate;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToTargetHybridPlayHighGoal extends CommandGroup {

    public GoToTargetHybridPlayHighGoal() {
        // TODO: Fix speeds and distances
        this.addSequential(new StraightDriveWithTime(0.0, 500));
        this.addSequential(new QuickRotate(60));
        this.addSequential(new ArmUpAuto(), 1);
        this.addSequential(new GetBallAuto(0.3), 1);
        this.addSequential(new StraightDriveWithEncoders(0.4, 6.0, 25));
    }
}
