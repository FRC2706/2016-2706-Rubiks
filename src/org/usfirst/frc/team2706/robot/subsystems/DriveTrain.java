package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {
	private Victor front_left_motor, back_left_motor,
							front_right_motor, back_right_motor;
	private RobotDrive drive;
	private Encoder left_encoder, right_encoder;
	private AnalogInput rangefinder;
	
	public double initGyro;

	public DriveTrain() {
		super();
		front_left_motor = new Victor(RobotMap.MOTOR_FRONT_LEFT);
		back_left_motor = new Victor(RobotMap.MOTOR_REAR_LEFT);
		front_right_motor = new Victor(RobotMap.MOTOR_FRONT_RIGHT);
		back_right_motor = new Victor(RobotMap.MOTOR_REAR_RIGHT);
		
		front_left_motor.setInverted(RobotMap.MOTOR_FRONT_LEFT_INVERTED);
		back_left_motor.setInverted(RobotMap.MOTOR_REAR_LEFT_INVERTED);
		front_right_motor.setInverted(RobotMap.MOTOR_FRONT_RIGHT_INVERTED);
		back_right_motor.setInverted(RobotMap.MOTOR_REAR_RIGHT_INVERTED);
		
		drive = new RobotDrive(front_left_motor, back_left_motor,
							   front_right_motor, back_right_motor);
		
		left_encoder = new Encoder(8, 7);
		right_encoder = new Encoder(0,1);

		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot move at some random value
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		if (Robot.isReal()) {
			left_encoder.setDistancePerPulse(RobotMap.ENCODER_LEFT_DPP);
			right_encoder.setDistancePerPulse(RobotMap.ENCODER_RIGHT_DPP);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			left_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
			right_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
		}

		// @TODO: Use RobotMap values
		rangefinder = new AnalogInput(6);
		
		reset();
		

		
		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front Left Motor", front_left_motor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor",  back_left_motor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor",  front_right_motor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", back_right_motor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", left_encoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", right_encoder);
		LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
	}

	/**
	 * When no other command is running let the operator drive around
	 * using the Xbox joystick.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Distance", left_encoder.getDistance());
		SmartDashboard.putNumber("Right Distance", right_encoder.getDistance());
		SmartDashboard.putNumber("Left Speed (RPM)", left_encoder.getRate());
		SmartDashboard.putNumber("Right Speed (RPM)", right_encoder.getRate());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}

	/**
	 * @param joy The Xbox style joystick to use to drive arcade style.
	 */
	public void drive(Joystick joy) {
		drive.arcadeDrive(RobotMap.INVERT_JOYSTICK_Y ? -joy.getRawAxis(3) : joy.getRawAxis(3), 
				RobotMap.INVERT_JOYSTICK_X ? -joy.getRawAxis(2) : joy.getRawAxis(2), true);
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		left_encoder.reset();
		right_encoder.reset();
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (left_encoder.getDistance() + right_encoder.getDistance())/2;
	}

	/**
	 * @return The robot's encoder PIDSource
	 */
	public PIDSource getEncoderPIDSource(boolean left) {
		if(left) {
			return left_encoder;
		}
		else {
			return right_encoder;
		}
	}
	
	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		return rangefinder.getAverageVoltage();
	}
	
	/**
	 * Sets the top speed the robot can drive at
	 */
	public void setTopSpeed(double topSpeed) {
		drive.setMaxOutput(Math.abs(topSpeed));
	}
}
