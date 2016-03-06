package org.usfirst.frc.team2706.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	/* README! ADDING NEW CONSTANTS:
	 * 
	 * Accessing constants with RobotMap is the same as usual,
	 * however, to add a new constant, you must first add the constant
	 * to RobotMap with new value.  Then create the same constant
	 * into each nested class, with the value for each robot.
	 * Next in the static block, add a local variable of the same type, 
	 * initialized to 0.  Go to the switch statement and set the local variable
	 * to the nested class version of the constant.  Finally, go to the last part
	 * of the static block and set the RobotMap constant to the local variable.
	 * 
	 * TL;DR (TODO)
	 * 
	 * That's it, you're done!
	 */
	
	// Left gearbox
			public static final int MOTOR_FRONT_LEFT = 2;
			public static final boolean MOTOR_FRONT_LEFT_INVERTED = true;
			
			// Right gearbox
			public static final int MOTOR_FRONT_RIGHT = 5;
			public static final boolean MOTOR_FRONT_RIGHT_INVERTED = true;
			
			// Right gearbox
			public static final int MOTOR_REAR_LEFT = 3;
			public static final boolean MOTOR_REAR_LEFT_INVERTED = true;
			
			// Left gearbox
			public static final int MOTOR_REAR_RIGHT = 6;
			public static final boolean MOTOR_REAR_RIGHT_INVERTED = true;

			public static final int CAN_INTAKE_LEFT = 2;
			public static final int CAN_INTAKE_RIGHT = 1;
			
			// Motor for panning camera
			public static final int MOTOR_CAMERA_PAN = 8;
			// Motor for tilting camera
			public static final int MOTOR_CAMERA_TILT = 9;

			// XXX: Encoders got flipped
			public static final int ENCODER_LEFT_A = 3;
			public static final int ENCODER_LEFT_B = 2;
			public static final double ENCODER_LEFT_DPP = 1.0 / 1075;

			public static final int ENCODER_RIGHT_A = 5;
			public static final int ENCODER_RIGHT_B = 6;
			public static final double ENCODER_RIGHT_DPP = 1.0 / 1075;

			public static final boolean INVERT_JOYSTICK_X = false;
			public static final boolean INVERT_JOYSTICK_Y = false;
			
			public static final int SELECTOR_CHANNEL = 0;
			
			// @TODO: Get Gyro channel, and rangefinder channel11
}
