package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class GetBall extends Command {
    public double speed;

    public GetBall(double speed) {
        this.speed = speed;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.intakeLeft.set(speed);
        Robot.intakeRight.set(-speed);
        Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected void end() {
        /*
         * Robot.intakeLeft.set(0.0); Robot.intakeRight.set(0.0);
         * Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
         */

    }

    @Override
    protected void interrupted() {
        end();

    }

}
