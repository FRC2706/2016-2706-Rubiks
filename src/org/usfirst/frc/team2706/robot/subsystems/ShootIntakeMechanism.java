package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ShootBallMotors;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/*
 * Platform motors that can intake and shoot the ball
 */
public class ShootIntakeMechanism extends Subsystem {
	
	
	
	
	private SpeedController left_motor, right_motor;
	
	private Encoder left_encoder, right_encoder;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShootBallMotors(0.5));
	}
	
	public ShootIntakeMechanism() {
		super();
		left_motor = new Talon(RobotMap.MOTOR_PLATFORM_LEFT);
		right_motor = new Talon(RobotMap.MOTOR_PLATFORM_RIGHT);
		
		
		// @TODO check whether this should be true or false
		left_motor.setInverted(false);
		right_motor.setInverted(false);
		
		// @TODO check where the encoder is supposed to be positioned
		left_encoder = new Encoder(1, 2);
		right_encoder = new Encoder(3, 4);
		
		// @TODO potentially add whether the ball is in or not
		LiveWindow.addActuator("ShootIntakeMechanism", "Left Motor", (Talon) left_motor);
		LiveWindow.addActuator("ShootIntakeMechanism", "Right Motor", (Talon)right_motor);
		LiveWindow.addActuator("ShootIntakeMechanism", "Left Encoder", left_encoder);
		LiveWindow.addActuator("ShootIntakeMechanism", "Right Encoder", right_encoder);
	}
	
	// assuming that one would want to only shoot the ball at max speed
	public void shoot(double speed) {
		left_motor.set(speed);
		right_motor.set(speed);	
	}
	
	public float getMotorSpeed() {
		return (float) left_motor.get();
	}
	
	// Speed is reversed for intakes at max speed
	public void pickupBall(double speed) {
		// set motors to intake
		left_motor.set(speed);
		right_motor.set(speed);
	}
}