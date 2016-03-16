package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class HighGoalShooter extends Command {
	public double speed;
	public HighGoalShooter(double speed) {
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

		// We're doing this timing in a separate thread and checking every 1 ms
		// rather than relying on the driver station's 50 hz loop, which can be +/- 20 ms
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run(){
				while(true) {
					if(System.currentTimeMillis() - 50 > startTime ) {
						new ArmDown().start();
					}

					if(System.currentTimeMillis() - 180 > startTime ) {
						Robot.ballKicker.set(DoubleSolenoid.Value.kForward);
						done = true;
						break;
					}
					Thread.sleep(1);
				}
			}
		});

		thread1.start();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return done;
	}

	@Override
	protected void end() {

	//	Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
