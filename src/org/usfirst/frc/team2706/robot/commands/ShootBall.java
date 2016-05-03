package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
    public double speed;

    public ShootBall(double speed) {
        this.speed = speed;
    }

    long startTime;

    @Override
    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    boolean done = false;

    @Override
    protected void execute() {
        Robot.intakeLeft.set(-speed);
        Robot.intakeRight.set(speed);
        if (System.currentTimeMillis() - 400 > startTime) {
            Robot.ballKicker.set(DoubleSolenoid.Value.kForward);
            done = true;
        }
        // }
        // else if(System.currentTimeMillis() - 700 > startTime) {
        // done = true;
        // }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return done;
    }

    @Override
    protected void end() {

        // Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        end();
    }

}
