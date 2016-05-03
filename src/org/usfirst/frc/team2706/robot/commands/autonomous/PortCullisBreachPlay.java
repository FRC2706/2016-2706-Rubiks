package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.ArmDownAuto;
import org.usfirst.frc.team2706.robot.commands.ArmUpAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PortCullisBreachPlay extends CommandGroup {
    public PortCullisBreachPlay() {
        this.addParallel(new ArmDownAuto(), 2);
        this.addSequential(new StraightDriveWithTime(0.50, 2500));
        this.addParallel(new ArmUpAuto(), 2);
        this.addSequential(new StraightDriveWithEncoders(0.65, 7, 25));

    }
}
