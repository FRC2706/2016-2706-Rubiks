package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBallAuto extends Command {
    public double speed;

    public ShootBallAuto(double speed) {
        this.speed = speed;
    }

    long startTime;

    @Override
    protected void initialize() {
        startTime = System.currentTimeMillis();
        donee = 0;
    }

    @Override
    protected void execute() {
        Robot.intakeLeft.set(-speed);
        Robot.intakeRight.set(speed);
        if (System.currentTimeMillis() - 600 > startTime) {
            Robot.ballKicker.set(DoubleSolenoid.Value.kForward);
        }
    }

    int donee = 0;

    @Override
    protected boolean isFinished() {
        if (++donee < 50) {
            return false;
        } else if (donee < 100) {
            Robot.ballKicker.set(DoubleSolenoid.Value.kForward);
            return false;
        } else {
            donee = 0;
            return true;
        }
    }

    @Override
    protected void end() {
        Robot.intakeLeft.set(0);
        Robot.intakeRight.set(0);
        // Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        end();
    }

}
