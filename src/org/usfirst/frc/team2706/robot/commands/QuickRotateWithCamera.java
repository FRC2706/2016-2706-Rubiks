package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetHybridPlay;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A very simple two-stage proportional control for quickly rotating the robot
 * heading based on gyro. Note this uses the current gyro heading and does NOT
 * reset it. Note that the input heading is ABSOLUTE (gyro based) and not
 * relative to current position, i.e. "90" means face the robot due east
 * (relative to the gyro heading), not turn 90 degrees from wherever it is now.
 */
public class QuickRotateWithCamera extends Command {

	// Gyro headings - what we want to be at, where we are now
	private double targetHeading;
	private double currentHeading;

	// Clockwise or counterclockwise rotation
	private int direction = 1;

	// Rotate faster if far away from target heading
	private double fastRotateSpeed = 0.7;

	// Rotate slower when approaching target heading
	private double slowRotateSpeed = 0.6;

	// Threshold (degrees) at which to switch from fast to slow
	private double speedThreshold = 20.0;

	// "Close enough"
	private double arrivalThreshold = 3.0;

	private boolean done = false;
	private GoToTargetHybridPlay commandGroup;
	
	public QuickRotateWithCamera(GoToTargetHybridPlay commandGroup) {
		this.commandGroup = commandGroup;
		requires(Robot.driveTrain);
	}

	StraightDriveWithEncoders se;

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.driveTrain.reset();
		targetHeading = normalize(commandGroup.cameraAngle + Robot.driveTrain.getHeading());
		done = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			currentHeading = normalize(Robot.driveTrain.getHeading());
			double error = normalize(targetHeading - currentHeading);

			System.out.println("Current Heading = " + currentHeading);
			System.out.println("Target Heading = " + targetHeading);
			System.out.println("error = " + error);

			// which direction are we off by?
			direction = (error > 0.0 ? -1 : 1);

			if (Math.abs(error) <= arrivalThreshold) {
				// close enough! We're done
				end();
				done = true;
			} else if (Math.abs(error) > speedThreshold) {
				// turn fast
				Robot.driveTrain.drive(direction * fastRotateSpeed, -direction * fastRotateSpeed);
			} else {
				// turn slow
				Robot.driveTrain.drive(direction * slowRotateSpeed, -direction * slowRotateSpeed);
			}
	}

	/** Gives a number between -180 and 180 **/
	private double normalize(double input) {
		double normalizedValue = input;
		while (normalizedValue > 180)
			normalizedValue -= 360;
		while (normalizedValue < -180)
			normalizedValue += 360;

		return normalizedValue;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
