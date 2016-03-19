package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.commands.plays.GoToTargetHybridPlay;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A very simple two-stage proportional control for quickly rotating the robot
 * heading based on gyro. Note this uses the current gyro heading and does NOT
 * reset it. Note that the input heading is ABSOLUTE (gyro based) and not
 * relative to current position, i.e. "90" means face the robot due east
 * (relative to the gyro heading), not turn 90 degrees from wherever it is now.
 */
public class QuickViewWithCamera extends Command {

	// Gyro headings - what we want to be at, where we are now
	private double targetHeading;

	private GoToTargetHybridPlay commandGroup;
	
	public QuickViewWithCamera(GoToTargetHybridPlay commandGroup) {
		this.commandGroup = commandGroup;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.driveTrain.reset();
		targetHeading = normalize(Robot.camera.RobotTurnDegrees() + Robot.driveTrain.getHeading());
		commandGroup.cameraAngle = targetHeading;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
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
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
