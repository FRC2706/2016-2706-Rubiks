package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class SearchForTarget extends Command {
    public static final float SEARCH_SPEED = 0.015f;
    public static final float SEARCH_TILT_BOTTOM = 0.7f;
    public static final float SEARCH_TILT_TOP = 0.4f;
    public static final float SEARCH_TILT_INCREMENT = 0.1f;
    public static final float SEARCH_PAN_RIGHT = 1;
    public static final float SEARCH_PAN_LEFT = 0;

    @Override
    protected void end() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute() {
        Look();
    }

    @Override
    protected void initialize() {
        System.out.println("Camera SmartLook Mode Initialized");
        Robot.camera.RawSetServoAxis(Camera.DEFAULT_PAN, SEARCH_TILT_BOTTOM);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean turningRight;

    public void Look() {
        if (turningRight) {
            Robot.camera.setRawX(Robot.camera.getCtrX() + SEARCH_SPEED);
        } else {
            Robot.camera.setRawX(Robot.camera.getCtrX() - SEARCH_SPEED);
        }
        if (Robot.camera.getCtrY() <= SEARCH_TILT_TOP) {
            Robot.camera.setRawY(SEARCH_TILT_BOTTOM);
        }
        if (Robot.camera.getCtrX() >= SEARCH_PAN_RIGHT) {
            turningRight = false;
            Robot.camera.setRawY(Robot.camera.getCtrY() - SEARCH_TILT_INCREMENT);
        } else if (Robot.camera.getCtrX() <= SEARCH_PAN_LEFT) {
            turningRight = true;
            Robot.camera.setRawY(Robot.camera.getCtrY() - SEARCH_TILT_INCREMENT);
        }
    }
}
