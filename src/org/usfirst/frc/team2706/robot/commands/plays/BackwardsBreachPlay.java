package org.usfirst.frc.team2706.robot.commands.plays;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.GetBall;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BackwardsBreachPlay extends CommandGroup {
    public BackwardsBreachPlay() {
        this.addSequential(new ArmDownAuto(), 2);
        // this.addSequential(new StraightDriveWithTime(0.0, 1000));
        this.addParallel(new GetBall(0.3), 4);
        this.addSequential(new StraightDriveWithEncoders(-0.6, -5, 25));
    }
}
