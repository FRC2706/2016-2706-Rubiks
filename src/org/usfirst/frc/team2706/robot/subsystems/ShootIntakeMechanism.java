package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.IntakeBall;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * Subsystem that controls the motors and pneumatics that
 * are on the platform
 */
public class ShootIntakeMechanism extends Subsystem {

	// Speed controllers for motors that intake and shoot the ball
	private SpeedController left_motor, right_motor;
	
	// Controllers for the pneumatics that output the ball
	private Compressor c;
	private Solenoid sol;
	
	public ShootIntakeMechanism() {
		super();
		
		left_motor = new Talon(RobotMap.MOTOR_PLATFORM_LEFT);
		right_motor = new Talon(RobotMap.MOTOR_PLATFORM_RIGHT);
		
		left_motor.setInverted(false);
		right_motor.setInverted(false);
		
		c = new Compressor(RobotMap.COMPRESSOR_SHOOT);
		sol = new Solenoid(RobotMap.SOLENOID_SHOOT);
		
		// Starts the compressor and allows it to run automatically
		c.start();
		c.setClosedLoopControl(true);
	}
	
	/*
	 * Method that turns the platform motors
	 * 
	 * @param speed sets the speed at which the motors are turning
	 */
	public void platformMotors(double speed) {
		left_motor.set(speed);
		right_motor.set(speed);
	}
	
	/*
	 * Method that turns the pneumatic either on or off
	 * 
	 *  @param position true = shoot, false means holding
	 */
	public void platformPneumatics(boolean position) {
		sol.set(position);
	}
	
	/*
	 * Method that returns the speed of the motors
	 */
	public double getMotorSpeed() {
		return left_motor.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IntakeBall(-0.5));
	}

}
