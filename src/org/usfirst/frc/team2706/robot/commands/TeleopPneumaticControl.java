package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopPneumaticControl extends Command {
	
	

	public static final double SHOOT_SPEED = 1.0;
	public static final double INTAKE_SPEED = 0.3;
	
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
		//boolean controlButtonX = Robot.oi.getOperatorJoystick().getRawButton(3);
		boolean controlButtonY = Robot.oi.getOperatorJoystick().getRawButton(4);
		boolean controlButtonLB = Robot.oi.getOperatorJoystick().getRawButton(5);
		boolean controlButtonRB = Robot.oi.getOperatorJoystick().getRawButton(6);
		/*boolean controlButtonBack = Robot.oi.getOperatorJoystick().getRawButton(7);
		boolean controlButtonStart = Robot.oi.getOperatorJoystick().getRawButton(8);
		boolean controlButtonLeftStick = Robot.oi.getOperatorJoystick().getRawButton(9);
		boolean controlButtonRightStick = Robot.oi.getOperatorJoystick().getRawButton(10);*/
		if(controlButtonLB) { 
			getBall = new GetBall(INTAKE_SPEED);
			getBall.start();
		}
		else if(controlButtonRB) {
			shootBall = new ShootBall(SHOOT_SPEED);
		shootBall.start();
		}
		else {
			Robot.intakeLeft.set(0.0);
			Robot.intakeRight.set(0.0);
			Robot.ballKicker.set(DoubleSolenoid.Value.kReverse);
		}
			
					
		if(controlButtonA)
			armDown.start();
		if(controlButtonY)
			armUp.start();
		
		if(controlButtonB)
			new FloatControl(true).start();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
