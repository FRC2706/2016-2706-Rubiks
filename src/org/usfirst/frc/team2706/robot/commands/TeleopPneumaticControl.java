package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopPneumaticControl extends Command {

	/** Booleans to track the state of various things so that we don't fire solinoids
	 * repeatedly and waste air. */
	boolean armIsDown = false;
	boolean inFloatMode = false;
	boolean ballShot = false;

	public static final double SHOOT_SPEED = 1.0;
	public static final double INTAKE_SPEED = 0.5;

	GetBall getBall = new GetBall(INTAKE_SPEED);
	ShootBall shootBall = new ShootBall(SHOOT_SPEED);
	ArmDown armDown = new ArmDown();
	ArmUp armUp = new ArmUp();
	//FloatControl floatControl = new FloatControl(true);
	@Override
	protected void initialize() {
	}
	@Override
	protected void execute() {
		boolean controlButtonA = Robot.oi.getOperatorJoystick().getRawButton(1);
		boolean controlButtonB = Robot.oi.getOperatorJoystick().getRawButton(2);
		boolean controlButtonX = Robot.oi.getOperatorJoystick().getRawButton(3);
		boolean controlButtonY = Robot.oi.getOperatorJoystick().getRawButton(4);
		boolean controlButtonLB = Robot.oi.getOperatorJoystick().getRawButton(5);
		boolean controlButtonRB = Robot.oi.getOperatorJoystick().getRawButton(6);
		double controlButtonRT = Robot.oi.getOperatorJoystick().getRawAxis(3);
		if(controlButtonLB) {
			getBall = new GetBall(INTAKE_SPEED);
			getBall.start();
		}
		else if(controlButtonRB) {
			if(!ballShot) {
				shootBall = new ShootBall(SHOOT_SPEED);
				shootBall.start();
				ballShot = true;
			}
		}
		else {
			Robot.intakeLeft.set(0.0);
			Robot.intakeRight.set(0.0);

			if(ballShot) {
				Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
				ballShot = false;
			}
		}


		if(controlButtonA) {
			//if(!armIsDown) {
				armDown.start();
				armIsDown = true;
			//}
		}
		if(controlButtonY) {
			//if(armIsDown) {
				armUp.start();
				armIsDown = false;
			//}
		}

		// buggy, commenting out guard code
		if(controlButtonB /**&& !inFloatMode**/) {
			new FloatControl(true).start();
			inFloatMode = true;
		}
		if(controlButtonX /**&& inFloatMode**/) {
			new FloatControl(false).start();
			inFloatMode = false;
		}
		if(controlButtonRT >= 1.0) {
			new HighGoalShooter(SHOOT_SPEED).start();
			armIsDown = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
