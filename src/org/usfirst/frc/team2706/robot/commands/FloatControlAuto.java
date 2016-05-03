package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class FloatControlAuto extends Command {
    public boolean value;

    public FloatControlAuto(boolean value) {
        this.value = value;
    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        Robot.armCylinder2
                        .set(value ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }

    int ticks = 0;

    @Override
    protected void execute() {
        // TODO Auto-generated method stub
        // this.cancel();
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        if (ticks++ < 20) {
            return false;
        } else {
            ticks = 0;
            return true;
        }
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub
        Robot.armCylinder2.set(DoubleSolenoid.Value.kOff);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        end();
    }

}
