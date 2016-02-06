package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Have the robot drive certain amount of time
 */
public class StraightDriveWithEncoders extends Command {
    
	private final double speed;
	
	private final double distance;
	
	private final PIDController pid;
	
	/**
	 * Drive at a specific speed for a certain amount of time
	 * 
	 * @param speed Speed in range [-1,1]
	 * @param angle The angle to rotate to
	 */
    public StraightDriveWithEncoders(double speed, double distance) {
        requires(Robot.driveTrain);

        this.speed = speed;
        
        this.distance = distance;
        
        // @TODO: Re-calibrate PID for this year
        pid = new PIDController(
       		SmartDashboard.getNumber("P", 0.05), 
       		SmartDashboard.getNumber("I", 0.01), 
       		SmartDashboard.getNumber("D", 0.01), 
       		SmartDashboard.getNumber("F", 0.5), 
       		Robot.driveTrain.getEncoderPIDSource(), 
       		Robot.driveTrain.getDrivePIDOutput(false)
        );
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.reset();
    	
    	// Make input infinite
    	pid.setContinuous();
    	// Set output speed range
    	pid.setOutputRange(-speed, speed);
    	// Will accept within 1 inch of target
    	pid.setAbsoluteTolerance(6.0/12);
    	
    	pid.setSetpoint(distance);
    	
    	// Start going to location
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Check if the PID is where it should be
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Disable PID output and stop robot to be safe
    	pid.disable();
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

