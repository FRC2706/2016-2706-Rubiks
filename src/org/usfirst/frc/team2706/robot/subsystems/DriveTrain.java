package org.usfirst.frc.team2706.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ArcadeDriveWithJoystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {
	private SpeedController front_left_motor, back_left_motor,
							front_right_motor, back_right_motor;
	private RobotDrive drive;
	private Encoder left_encoder, right_encoder;
	private AnalogInput rangefinder;
	private AnalogGyro gyro;

	public DriveTrain() {
		super();
		front_left_motor = new Talon(RobotMap.MOTOR_FRONT_LEFT);
		back_left_motor = new Talon(RobotMap.MOTOR_REAR_LEFT);
		front_right_motor = new Talon(RobotMap.MOTOR_FRONT_RIGHT);
		back_right_motor = new Talon(RobotMap.MOTOR_REAR_RIGHT);
		
		front_left_motor.setInverted(true);
		back_left_motor.setInverted(true);
		front_right_motor.setInverted(true);
		back_right_motor.setInverted(true);
		
		drive = new RobotDrive(front_left_motor, back_left_motor,
							   front_right_motor, back_right_motor);
		
		// @TODO: Use RobotMap values
		left_encoder = new Encoder(1, 2);
		right_encoder = new Encoder(3, 4);

		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		if (Robot.isReal()) {
			left_encoder.setDistancePerPulse(0.042);
			right_encoder.setDistancePerPulse(0.042);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			left_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
			right_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
		}

		// @TODO: Use RobotMap values
		rangefinder = new AnalogInput(6);
		gyro = new AnalogGyro(1);

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) front_left_motor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) back_left_motor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) front_right_motor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) back_right_motor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", left_encoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", right_encoder);
		LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
		LiveWindow.addSensor("Drive Train", "Gyro", gyro);
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
		SmartDashboard.putNumber("Left Speed", left_encoder.getRate());
		SmartDashboard.putNumber("Right Speed", right_encoder.getRate());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
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
		drive.arcadeDrive(joy);
	}

	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return gyro.getAngle();
	}
	
	/**
	 * @param invert True to invert second motor direction for rotating
	 * 
	 * @return The robot's drive PIDOutput
	 */
	public PIDOutput getDrivePIDOutput(final boolean invert) {
		DriveTrain ref = this;
		return new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				ref.drive(output, invert ? output : -output);
				
			}
			
		};
	}
	
	/**
	 * @return The robot's gyro PIDSource
	 */
	public PIDSource getGyroPIDSource() {
		DriveTrain ref = this;
		return new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {};

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				return ref.getHeading();
			}
			
		};
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
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
	public PIDSource getEncoderPIDSource() {
		DriveTrain ref = this;
		return new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {};

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				return ref.getDistance();
			}
			
		};
	}
	
	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		return rangefinder.getAverageVoltage();
	}
}
