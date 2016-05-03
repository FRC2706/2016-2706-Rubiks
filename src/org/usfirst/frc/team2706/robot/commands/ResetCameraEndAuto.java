package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class ResetCameraEndAuto extends Command {
    @Override
    protected void initialize() {
        Robot.camera.turnXAxis.set((float) Camera.DEFAULT_PAN);
        Robot.camera.turnXAxis.set((float) Camera.DEFAULT_TILT);
    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

}
