package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive arcade style using the Xbox Joystick until interrupted.
 */
public class ArcadeDriveWithJoystick extends Command {

    public ArcadeDriveWithJoystick() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.drive(Robot.oi.getDriverJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
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
