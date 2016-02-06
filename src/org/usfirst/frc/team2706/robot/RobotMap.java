package org.usfirst.frc.team2706.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Left gearbox
	public static final int MOTOR_FRONT_LEFT = 3;
	// Right gearbox
	public static final int MOTOR_FRONT_RIGHT = 0;
	// Right gearbox
	public static final int MOTOR_REAR_RIGHT = 1;
	// Left gearbox
	public static final int MOTOR_REAR_LEFT = 2;
	
	public static final int CAN_ADDR_PNEUMATIC = 0;
	
	// XXX: Encoders got flipped
	public static final int ENCODER_LEFT_A = 2;
	public static final int ENCODER_LEFT_B = 3;
	public static final double ENCODER_LEFT_DPP = 1.0/1960;
	
	public static final int ENCODER_RIGHT_A = 1;
	public static final int ENCODER_RIGHT_B = 0;
	public static final double ENCODER_RIGHT_DPP = 1.0/1960;
	
	// @TODO: Get Gyro channel, and rangefinder channel11
}
