package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class HighGoalShooter extends Command {
    public double speed;

    public HighGoalShooter(double speed) {
        this.speed = speed;
    }


    boolean started = false;
    boolean done = false;

    @Override
    protected void initialize() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                long startTime = System.currentTimeMillis();
                boolean armFired = false;

                while (true) {

                    Robot.intakeLeft.set(-speed);
                    Robot.intakeRight.set(speed);

                    if (System.currentTimeMillis() - 80 > startTime) {
                        if (!armFired) {
                            new ArmDown().start();
                            armFired = true;
                        }
                    }

                    if (System.currentTimeMillis() - 360 > startTime) {
                        Robot.ballKicker.set(DoubleSolenoid.Value.kForward);
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                done = true;
            }
        });

        thread1.start();

    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return done;
    }

    @Override
    protected void end() {

        Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        end();
    }

}
