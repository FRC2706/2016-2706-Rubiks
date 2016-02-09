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
	
	// @TODO find out whether this is the pneumatic for the ball shooting
	public static final int CAN_ADDR_PNEUMATIC = 0;
	
	// @TODO: Get channel of motors and pneumatics that shoot
	// Using drive motors now
	public static final int MOTOR_PLATFORM_LEFT = 3;
	public static final int MOTOR_PLATFORM_RIGHT = 0;
	
	// Pneumatic and compressor for shooting
	// @TODO find out the port number of all the ports
	public static final int SOLENOID_SHOOT = 0;
	public static final int COMPRESSOR_SHOOT = 0;
	
	// @TODO: Get encoder channels, gyro channel, and rangefinder channel
}
