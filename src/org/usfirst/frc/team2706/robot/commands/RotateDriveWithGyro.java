package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive certain amount of time
 */
public class RotateDriveWithGyro extends Command {
    
	private final double speed;
	
	private final double angle;
	
	private final PIDController PID;
	
	/**
	 * Drive at a specific speed for a certain amount of time
	 * 
	 * @param speed Speed in range [-1,1]
	 * @param angle The angle to rotate to
	 */
    public RotateDriveWithGyro(double speed, double angle) {
        requires(Robot.driveTrain);

        this.speed = speed;
        
        this.angle = angle;
        
        // @FIXME: Random values
        PID = new PIDController(2, 6, 1, 5, Robot.driveTrain.getGyroPIDSource(), 
        		Robot.driveTrain.getDrivePIDOutput(true));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.reset();
    	
    	// Make input infinite
    	PID.setContinuous();
    	// Set output speed range
    	PID.setOutputRange(-speed, speed);
    	// Will accept within 5 degrees of target
    	PID.setAbsoluteTolerance(5);
    	
    	PID.setSetpoint(angle);
    	
    	// Start going to location
    	PID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Check if the PID is where it should be
        return PID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Disable PID output and stop robot to be safe
    	PID.disable();
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
