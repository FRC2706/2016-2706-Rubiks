package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/*
 * Platform motors and pneumatics ( should pneumatics be a separate file )
 * that intakes and then shoot the ball
 */
public class ShootIntakeMechanism extends Subsystem {
	
	// @TODO find out whether it is supposed to be at 0
	private Compressor compress;
	private Solenoid pneu;
	
	private SpeedController left_motor, right_motor;
	
	private Encoder left_encoder, right_encoder;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public ShootIntakeMechanism() {
		super();
		left_motor = new Talon(RobotMap.MOTOR_PLATFORM_LEFT);
		right_motor = new Talon(RobotMap.MOTOR_PLATFORM_RIGHT);
		
		compress = new Compressor(RobotMap.COMPRESSOR_SHOOT);
		pneu = new Solenoid(RobotMap.SOLENOID_SHOOT);
		compress.start();
		compress.setClosedLoopControl(true);
		
		// @TODO check whether this should be true or false
		left_motor.setInverted(false);
		right_motor.setInverted(false);
		
		// @TODO check where the encoder is supposed to be
		left_encoder = new Encoder(1, 2);
		right_encoder = new Encoder(3, 4);
		
		// @TODO potentially add whether the ball is in or not
		LiveWindow.addActuator("Shooting Mechanism", "Left Motor", (Talon) left_motor);
		LiveWindow.addActuator("Shooting Mechanism", "Right Motor", (Talon)right_motor);
		LiveWindow.addActuator("Shooting Mechanism", "Left Encoder", left_encoder);
		LiveWindow.addActuator("Shooting Mechanism", "Right Encoder", right_encoder);
		// @TODO is this the right way to set up the Live window for the pneumatics?
		LiveWindow.addActuator("Shooting Mechanism", "Pneumatic Punch", pneu);
		LiveWindow.addActuator("Shooting Mechanism", "Compressor", compress);
		
	}
	
	// assuming that one would want to only shoot the ball at max speed
	public void shoot(double speed) {
		left_motor.set(speed);
		right_motor.set(speed);
		
	}
	
	// pneumatic punch that hits the ball after the motors are on
	public void pneumaticPunch() {
		pneu.set(true);
	}
	
	// Speed is reversed for intakes at max speed
	public void pickupBall(double speed) {
		// make sure pneumatic is reset 
		pneu.set(false);
		// set motors to intake
		left_motor.set(speed);
		right_motor.set(speed);
	}
}
