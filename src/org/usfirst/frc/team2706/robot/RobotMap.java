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
	//Motor for panning camera
	public static final int MOTOR_CAMERA_PAN = 8;
	//Motor for tilting camera
	public static final int MOTOR_CAMERA_TILT = 9;
	// @TODO: Get encoder channels, gyro channel, and rangefinder channel
}
