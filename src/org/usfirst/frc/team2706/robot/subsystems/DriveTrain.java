package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ArcadeDriveWithJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
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
	
	private ComposedMotor leftMotor, rightMotor;

	private RobotDrive drive;
	private Encoder left_encoder, right_encoder;
	private AnalogInput rangefinder;
	private AHRS gyro;
	
	public double initGyro;

	public DriveTrain() {
		super();
		front_left_motor = new Victor(RobotMap.MOTOR_FRONT_LEFT);
		back_left_motor = new Victor(RobotMap.MOTOR_REAR_LEFT);
		front_right_motor = new Victor(RobotMap.MOTOR_FRONT_RIGHT);
		back_right_motor = new Victor(RobotMap.MOTOR_REAR_RIGHT);
		
		leftMotor = new ComposedMotor(front_left_motor, back_left_motor);
		rightMotor = new ComposedMotor(front_right_motor, back_right_motor);
		
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
		
		// Set up navX gyro
		gyro = new AHRS(SPI.Port.kMXP);
		while(gyro.isCalibrating()) {
			;
		}
		
		reset();
		

		
		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front Left Motor", front_left_motor);
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
	public void arcadeDrive(Joystick joy) {
		drive.arcadeDrive(RobotMap.INVERT_JOYSTICK_Y ? -joy.getRawAxis(5) : joy.getRawAxis(5), 
				RobotMap.INVERT_JOYSTICK_X ? -joy.getRawAxis(4) : joy.getRawAxis(4), true);
	}

	public void arcadeDrive(double rotate, double forward) {
		drive.arcadeDrive(rotate, forward);
	}
	
	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		left_encoder.reset();
		right_encoder.reset();
	}
	
	/**
	 * Reset the robot gyro to the zero state.
	 */
	public void resetGyro() {
		gyro.reset();
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
			leftMotor.setInverted(invert);
			return leftMotor;
		}
		else {
			rightMotor.setInverted(invert);
			return rightMotor;
		}
	}
	
	public double getPIDForwardOutput(boolean left) {
		if(left)
			return leftMotor.getPIDForwardOutput();
		else
			return rightMotor.getPIDForwardOutput();
	}
	
	public double getPIDRotateOutput(boolean left) {
		if(left)
			return leftMotor.getPIDRotateOutput();
		else
			return rightMotor.getPIDRotateOutput();
	}
	
	/**
	 * @return The robot's gyro PIDSource
	 */
	public PIDSource getGyroPIDSource(boolean invert) {
		return gyro;
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

	private class ComposedMotor implements SpeedController {

		final SpeedController motorA;
		final SpeedController motorB;
		
		private boolean inverted;
		
		private double pidForward;
		private double pidRotate;
		
		public ComposedMotor(SpeedController motorA, SpeedController motorB) {
			this.motorA = motorA;
			this.motorB = motorB;
		}
		
		@Override
		public void pidWrite(double output) {
			pidForward = inverted ? -output : output;
			pidRotate  = (normalize(getHeading() - initGyro) * 0.1);
		}

		@Override
		public double get() {
			return motorA.get();
		}

		@Override
		public void set(double speed, byte syncGroup) {
			motorA.set(speed, syncGroup);
			motorB.set(speed, syncGroup);
			
		}

		@Override
		public void set(double speed) {
			motorA.set(speed);
			motorB.set(speed);
			
		}

		@Override
		public void setInverted(boolean isInverted) {
			inverted = isInverted;
			
		}

		@Override
		public boolean getInverted() {
			return inverted;
		}

		@Override
		public void disable() {
			motorA.disable();
			motorB.disable();
		}

		@Override
		public void stopMotor() {
			motorA.stopMotor();
			motorB.stopMotor();
		}
		
		public double getPIDForwardOutput() {
			return pidForward;
		}
		
		public double getPIDRotateOutput() {
			return pidRotate;
		}
	}
	
    private double normalize(double input) {
    	double normalizedValue = input;
    	while (normalizedValue > 180)
    		normalizedValue -= 360;
    	while (normalizedValue < -180)
    		normalizedValue +=360;
    	
		return normalizedValue;
	}	
}
