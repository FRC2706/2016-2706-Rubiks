package org.usfirst.frc.team2706.robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	public static final int MOTOR_FRONT_LEFT;
	public static final boolean MOTOR_FRONT_LEFT_INVERTED;
	
	// Right gearbox
	public static final int MOTOR_FRONT_RIGHT;
	public static final boolean MOTOR_FRONT_RIGHT_INVERTED;
	
	// Right gearbox
	public static final int MOTOR_REAR_LEFT;
	public static final boolean MOTOR_REAR_LEFT_INVERTED;
	
	// Left gearbox
	public static final int MOTOR_REAR_RIGHT;
	public static final boolean MOTOR_REAR_RIGHT_INVERTED;
	
	public static final int CAN_ADDR_PNEUMATIC;

	// Motor for panning camera
	public static final int MOTOR_CAMERA_PAN;
	// Motor for tilting camera
	public static final int MOTOR_CAMERA_TILT;
	
	// XXX: Encoders got flipped
	public static final int ENCODER_LEFT_A;
	public static final int ENCODER_LEFT_B;
	public static final double ENCODER_LEFT_DPP;

	public static final int ENCODER_RIGHT_A;
	public static final int ENCODER_RIGHT_B;
	public static final double ENCODER_RIGHT_DPP;

	public static final boolean INVERT_JOYSTICK_X;
	public static final boolean INVERT_JOYSTICK_Y;
	
	public static final int SELECTOR_CHANNEL;
	
	// @TODO: Get Gyro channel, and rangefinder channel11

	static {

		int motorFrontLeft = 0;
		boolean motorFrontLeftInverted = false;
		
		int motorFrontRight = 0;
		boolean motorFrontRightInverted = false;
		
		int motorRearLeft = 0;
		boolean motorRearLeftInverted = false;
		
		int motorRearRight = 0;
		boolean motorRearRightInverted = false;
		
		int canAddrPneumatic = 0;

		int motorCameraPan = 0;
		int motorCameraTilt = 0;
		
		int encoderLeftA = 0;
		int encoderLeftB = 0;
		double encoderLeftDPP = 0.0;

		int encoderRightA = 0;
		int encoderRightB = 0;
		double encoderRightDPP = 0.0;
		
		boolean invertJoystickX = false;
		boolean invertJoystickY = false;
		
		int selectorChannel = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/lvuser/robot-type.conf"));

			// Defaults to competition robot
			int robotType = 0;

			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.trim();
				boolean isNumber = true;
				try {
					Integer.parseInt(line);
				} catch (NumberFormatException e) {
					isNumber = false;
				}

				if (isNumber) {
					robotType = Integer.parseInt(line);
					break;
				}
				br.close();
			}
			switch (robotType) {
			// Competition
			case 0:
				motorFrontLeft = CompetitionRobotMap.MOTOR_FRONT_LEFT;
				motorFrontLeftInverted = CompetitionRobotMap.MOTOR_FRONT_LEFT_INVERTED;
				motorFrontRight = CompetitionRobotMap.MOTOR_FRONT_RIGHT;
				motorFrontRightInverted = CompetitionRobotMap.MOTOR_FRONT_RIGHT_INVERTED;
				motorRearLeft = CompetitionRobotMap.MOTOR_REAR_LEFT;
				motorRearLeftInverted = CompetitionRobotMap.MOTOR_REAR_LEFT_INVERTED;
				motorRearRight = CompetitionRobotMap.MOTOR_REAR_RIGHT;
				motorRearRightInverted = CompetitionRobotMap.MOTOR_REAR_RIGHT_INVERTED;
				canAddrPneumatic = CompetitionRobotMap.CAN_ADDR_PNEUMATIC;
				motorCameraPan = CompetitionRobotMap.MOTOR_CAMERA_PAN;
				motorCameraTilt = CompetitionRobotMap.MOTOR_CAMERA_TILT;
				encoderLeftA = CompetitionRobotMap.ENCODER_LEFT_A;
				encoderLeftB = CompetitionRobotMap.ENCODER_LEFT_B;
				encoderLeftDPP = CompetitionRobotMap.ENCODER_LEFT_DPP;
				encoderRightA = CompetitionRobotMap.ENCODER_RIGHT_A;
				encoderRightB = CompetitionRobotMap.ENCODER_LEFT_B;
				encoderRightDPP = CompetitionRobotMap.ENCODER_RIGHT_DPP;
				invertJoystickX = CompetitionRobotMap.INVERT_JOYSTICK_X;
				invertJoystickY = CompetitionRobotMap.INVERT_JOYSTICK_Y;
				selectorChannel = CompetitionRobotMap.SELECTOR_CHANNEL;
				break;
			// Practice
			case 1:
				motorFrontLeft = PracticeRobotMap.MOTOR_FRONT_LEFT;
				motorFrontLeftInverted = PracticeRobotMap.MOTOR_FRONT_LEFT_INVERTED;
				motorFrontRight = PracticeRobotMap.MOTOR_FRONT_RIGHT;
				motorFrontRightInverted = PracticeRobotMap.MOTOR_FRONT_RIGHT_INVERTED;
				motorRearLeft = PracticeRobotMap.MOTOR_REAR_LEFT;
				motorRearLeftInverted = PracticeRobotMap.MOTOR_REAR_LEFT_INVERTED;
				motorRearRight = PracticeRobotMap.MOTOR_REAR_RIGHT;
				motorRearRightInverted = PracticeRobotMap.MOTOR_REAR_RIGHT_INVERTED;
				canAddrPneumatic = PracticeRobotMap.CAN_ADDR_PNEUMATIC;
				motorCameraPan = PracticeRobotMap.MOTOR_CAMERA_PAN;
				motorCameraTilt = PracticeRobotMap.MOTOR_CAMERA_TILT;
				encoderLeftA = PracticeRobotMap.ENCODER_LEFT_A;
				encoderLeftB = PracticeRobotMap.ENCODER_LEFT_B;
				encoderLeftDPP = PracticeRobotMap.ENCODER_LEFT_DPP;
				encoderRightA = PracticeRobotMap.ENCODER_RIGHT_A;
				encoderRightB = PracticeRobotMap.ENCODER_RIGHT_B;
				encoderRightDPP = PracticeRobotMap.ENCODER_RIGHT_DPP;
				invertJoystickX = PracticeRobotMap.INVERT_JOYSTICK_X;
				invertJoystickY = PracticeRobotMap.INVERT_JOYSTICK_Y;
				selectorChannel = PracticeRobotMap.SELECTOR_CHANNEL;
				break;
			case 2:
				// Simulation
				motorFrontLeft = SimulationRobotMap.MOTOR_FRONT_LEFT;
				motorFrontLeftInverted = SimulationRobotMap.MOTOR_FRONT_LEFT_INVERTED;
				motorFrontRight = SimulationRobotMap.MOTOR_FRONT_RIGHT;
				motorFrontRightInverted = SimulationRobotMap.MOTOR_FRONT_RIGHT_INVERTED;
				motorRearLeft = SimulationRobotMap.MOTOR_REAR_LEFT;
				motorRearLeftInverted = SimulationRobotMap.MOTOR_REAR_LEFT_INVERTED;
				motorRearRight = SimulationRobotMap.MOTOR_REAR_RIGHT;
				motorRearRightInverted = SimulationRobotMap.MOTOR_REAR_RIGHT_INVERTED;
				canAddrPneumatic = SimulationRobotMap.CAN_ADDR_PNEUMATIC;
				motorCameraPan = SimulationRobotMap.MOTOR_CAMERA_PAN;
				motorCameraTilt = SimulationRobotMap.MOTOR_CAMERA_TILT;
				encoderLeftA = SimulationRobotMap.ENCODER_LEFT_A;
				encoderLeftB = SimulationRobotMap.ENCODER_LEFT_B;
				encoderLeftDPP = SimulationRobotMap.ENCODER_LEFT_DPP;
				encoderRightA = SimulationRobotMap.ENCODER_RIGHT_A;
				encoderRightB = SimulationRobotMap.ENCODER_RIGHT_B;
				encoderRightDPP = SimulationRobotMap.ENCODER_RIGHT_DPP;
				invertJoystickX = SimulationRobotMap.INVERT_JOYSTICK_X;
				invertJoystickY = SimulationRobotMap.INVERT_JOYSTICK_Y;
				selectorChannel = SimulationRobotMap.SELECTOR_CHANNEL;
				break;
			default:
				motorFrontLeft = CompetitionRobotMap.MOTOR_FRONT_LEFT;
				motorFrontLeftInverted = CompetitionRobotMap.MOTOR_FRONT_LEFT_INVERTED;
				motorFrontRight = CompetitionRobotMap.MOTOR_FRONT_RIGHT;
				motorFrontRightInverted = CompetitionRobotMap.MOTOR_FRONT_RIGHT_INVERTED;
				motorRearLeft = CompetitionRobotMap.MOTOR_REAR_LEFT;
				motorRearLeftInverted = CompetitionRobotMap.MOTOR_REAR_LEFT_INVERTED;
				motorRearRight = CompetitionRobotMap.MOTOR_REAR_RIGHT;
				motorRearRightInverted = CompetitionRobotMap.MOTOR_REAR_RIGHT_INVERTED;
				canAddrPneumatic = CompetitionRobotMap.CAN_ADDR_PNEUMATIC;
				motorCameraPan = CompetitionRobotMap.MOTOR_CAMERA_PAN;
				motorCameraTilt = CompetitionRobotMap.MOTOR_CAMERA_TILT;
				encoderLeftA = CompetitionRobotMap.ENCODER_LEFT_A;
				encoderLeftB = CompetitionRobotMap.ENCODER_LEFT_B;
				encoderLeftDPP = CompetitionRobotMap.ENCODER_LEFT_DPP;
				encoderRightA = CompetitionRobotMap.ENCODER_RIGHT_A;
				encoderRightB = CompetitionRobotMap.ENCODER_RIGHT_B;
				encoderRightDPP = CompetitionRobotMap.ENCODER_RIGHT_DPP;
				invertJoystickX = CompetitionRobotMap.INVERT_JOYSTICK_X;
				invertJoystickY = CompetitionRobotMap.INVERT_JOYSTICK_Y;
				selectorChannel = CompetitionRobotMap.SELECTOR_CHANNEL;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MOTOR_FRONT_LEFT = motorFrontLeft;
		MOTOR_FRONT_LEFT_INVERTED = motorFrontLeftInverted;
		
		MOTOR_FRONT_RIGHT = motorFrontRight;
		MOTOR_FRONT_RIGHT_INVERTED = motorFrontRightInverted;
		
		MOTOR_REAR_LEFT = motorRearLeft;
		MOTOR_REAR_LEFT_INVERTED = motorRearLeftInverted;
		
		MOTOR_REAR_RIGHT = motorRearRight;
		MOTOR_REAR_RIGHT_INVERTED = motorRearRightInverted;
		
		CAN_ADDR_PNEUMATIC = canAddrPneumatic;
		
		MOTOR_CAMERA_PAN = motorCameraPan;
		MOTOR_CAMERA_TILT = motorCameraTilt;
		
		ENCODER_LEFT_A = encoderLeftA;
		ENCODER_LEFT_B = encoderLeftB;
		ENCODER_LEFT_DPP = encoderLeftDPP;
		
		ENCODER_RIGHT_A = encoderRightA;
		ENCODER_RIGHT_B = encoderRightB;
		ENCODER_RIGHT_DPP = encoderRightDPP;
		
		INVERT_JOYSTICK_X = invertJoystickX;
		INVERT_JOYSTICK_Y = invertJoystickY;
		
		SELECTOR_CHANNEL = selectorChannel;
	}

	private class CompetitionRobotMap extends RobotMap {
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
		
		public static final int CAN_ADDR_PNEUMATIC = 0;
		
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

	private class PracticeRobotMap extends RobotMap {
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

		public static final int CAN_ADDR_PNEUMATIC = 0;
		
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

	private class SimulationRobotMap extends RobotMap {
		// Left gearbox
		public static final int MOTOR_FRONT_LEFT = 3;
		public static final boolean MOTOR_FRONT_LEFT_INVERTED = false;
		
		// Right gearbox
		public static final int MOTOR_FRONT_RIGHT = 0;
		public static final boolean MOTOR_FRONT_RIGHT_INVERTED = false;
		
		// Right gearbox
		public static final int MOTOR_REAR_LEFT = 2;
		public static final boolean MOTOR_REAR_LEFT_INVERTED = false;
		
		// Left gearbox
		public static final int MOTOR_REAR_RIGHT = 1;
		public static final boolean MOTOR_REAR_RIGHT_INVERTED = false;

		public static final int CAN_ADDR_PNEUMATIC = 0;
		
		// Motor for panning camera
		public static final int MOTOR_CAMERA_PAN = 8;
		// Motor for tilting camera
		public static final int MOTOR_CAMERA_TILT = 9;

		// XXX: Encoders got flipped
		public static final int ENCODER_LEFT_A = 2;
		public static final int ENCODER_LEFT_B = 3;
		public static final double ENCODER_LEFT_DPP = 1.0 / 1960;

		public static final int ENCODER_RIGHT_A = 1;
		public static final int ENCODER_RIGHT_B = 0;
		public static final double ENCODER_RIGHT_DPP = 1.0 / 1960;

		public static final boolean INVERT_JOYSTICK_X = false;
		public static final boolean INVERT_JOYSTICK_Y = false;
		
		public static final int SELECTOR_CHANNEL = 0;
		
		// @TODO: Get Gyro channel, and rangefinder channel11
	}
}
