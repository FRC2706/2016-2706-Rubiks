package org.usfirst.frc.team2706.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
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
	private Victor front_left_motor, back_left_motor,
							front_right_motor, back_right_motor;
	private RobotDrive drive;
	private Encoder left_encoder, right_encoder;
	private AnalogInput rangefinder;
	private AnalogGyro gyro;
	
	private GyroPIDSource gyroPIDSource;

	private DrivePIDOutput leftDrivePIDOutput;
	private DrivePIDOutput rightDrivePIDOutput;

	public DriveTrain() {
		super();
		front_left_motor = new Victor(RobotMap.MOTOR_FRONT_LEFT);
		back_left_motor = new Victor(RobotMap.MOTOR_REAR_LEFT);
		front_right_motor = new Victor(RobotMap.MOTOR_FRONT_RIGHT);
		back_right_motor = new Victor(RobotMap.MOTOR_REAR_RIGHT);
		
		front_left_motor.setInverted(true);
		back_left_motor.setInverted(true);
		front_right_motor.setInverted(true);
		back_right_motor.setInverted(true);
		
		drive = new RobotDrive(front_left_motor, back_left_motor,
							   front_right_motor, back_right_motor);
		
		left_encoder = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);
		right_encoder = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B);

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
		gyro = new AnalogGyro(1);
		
		gyroPIDSource = new GyroPIDSource(this);
		
		leftDrivePIDOutput = new DrivePIDOutput(new RobotDrive(front_left_motor, back_left_motor));
		rightDrivePIDOutput = new DrivePIDOutput(new RobotDrive(front_right_motor, back_right_motor));

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", front_left_motor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor",  back_left_motor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor",  front_right_motor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", back_right_motor);
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
		SmartDashboard.putNumber("Left Speed (RPM)", left_encoder.getRate());
		SmartDashboard.putNumber("Right Speed (RPM)", right_encoder.getRate());
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
	public PIDOutput getDrivePIDOutput(boolean invert, boolean left) {
		if(left) {
			leftDrivePIDOutput.invert(invert);
			return leftDrivePIDOutput;
		}
		else {
			rightDrivePIDOutput.invert(invert);
			return rightDrivePIDOutput;
		}
	}
	
	/**
	 * @return The robot's gyro PIDSource
	 */
	public PIDSource getGyroPIDSource(boolean invert) {
		gyroPIDSource.invert(invert);
		return gyroPIDSource;
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
	
	class GyroPIDSource implements PIDSource {

		private final DriveTrain driveTrain;
		private boolean invert;
		
		public GyroPIDSource(DriveTrain driveTrain) {
			this.driveTrain = driveTrain;
		}
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			driveTrain.gyro.setPIDSourceType(pidSource);
		};

		@Override
		public PIDSourceType getPIDSourceType() {
			return driveTrain.gyro.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return invert ? driveTrain.getHeading() : -driveTrain.getHeading();
		}
		
		
		public void invert(boolean invert) {
			this.invert = invert;
		}
	}
	
	class DrivePIDOutput implements PIDOutput {

		private final RobotDrive drive;	
		
		boolean invert = false;
		
		public DrivePIDOutput(RobotDrive drive) {
			this.drive = drive;
		}

		public void invert(boolean invert) {
			this.invert = invert;
		}
		
		@Override
		public void pidWrite(double output) {
			// XXX: Motors must be opposite to avoid fighting
			drive.tankDrive(invert ? output : -output, invert ? -output : output);
		}		
	}
}
