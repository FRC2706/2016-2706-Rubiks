package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive certain distance
 */
public class StraightDriveWithEncoders extends Command {
    
	private final double speed;
	
	private final double distance;
	
	private final int minDoneCycles;
	
	private PIDController PID;
	
	private int doneCount;
	
	private final double P=1.0, I=0.0, D=0.25;
	
	/**
	 * Drive at a specific speed for a certain amount of time
	 * 
	 * @param speed Speed in range [-1,1]
	 * @param distance The encoder distance to travel
	 * @param minDoneCycles The amount of cycles when the robot is within its target range to end the comman
	 */
    public StraightDriveWithEncoders(double speed, double distance, int minDoneCycles) {
        requires(Robot.driveTrain);

        this.speed = speed;
        
        this.distance = distance;
        
        this.minDoneCycles = minDoneCycles;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Will get all inverted if called multiple times from different constructors
        PID = new PIDController(P,I,D,	 
       		Robot.driveTrain.getEncoderPIDSource(true), 
       		Robot.driveTrain.getDrivePIDOutput(false, true)
        );
    	
    	Robot.driveTrain.reset();
    	
    	// Make input infinite
    	PID.setContinuous();
    	
    	// Set output speed range
    	if(speed > 0) {
    		PID.setOutputRange(-speed, speed);
    	}
    	else {
    		PID.setOutputRange(speed, -speed);
    	}

		Robot.driveTrain.initGyro = Robot.driveTrain.getHeading();
		
    	PID.setSetpoint(distance);
    	
    	
    	// Will accept within 5 inch of target
    	PID.setAbsoluteTolerance(5.0/12);
    	
    	// Start going to location
    	PID.enable();
    	//rightPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	Robot.driveTrain.arcadeDrive(Robot.driveTrain.getPIDForwardOutput(true), Robot.driveTrain.getPIDRotateOutput(true));
    	
    	// TODO: Use WPI onTarget()
    	onTarget();
    }
    public boolean done;
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(this.doneCount > this.minDoneCycles) {
    		done = true;
    		return true;
    	
    	}
    	else {
    		//done = false;
    		return false;
    	}

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
    
    private boolean onTarget() {
    	if(PID.getError() < 5.0/12) {
    		doneCount++;
    		return true;
    	}
    	else {
    		doneCount = 0;
    		return false;
    	}
    		
    }
    
    public int getDoneCount() {
    	return doneCount;
    }
}

