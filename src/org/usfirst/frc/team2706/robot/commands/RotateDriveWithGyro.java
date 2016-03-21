package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This mostly works, but use the QuickRotate command instead. 
 * PID control using gyro heading is slower and needs tuning of P,I,D parameters.
 * 
 * Note that the gyro heading is now absolute and not relative, so angle is a target heading
 * and not a relative turn angle.
 */
public class RotateDriveWithGyro extends Command {
    
	private final double speed;
	
	private final double angle;
	
	private final int minDoneCycles;
	
	private PIDController leftPID;
	private PIDController rightPID;
	
	private int doneCount;
	
	private final double P=2, I=0.05, D=0.05, F=0;
	
	/**
	 * Drive at a specific speed for a certain amount of time
	 * 
	 * @param speed Speed in range [-1,1]
	 * @param angle The angle to rotate to
	 */
    public RotateDriveWithGyro(double speed, double angle, int minDoneCycles) {
        requires(Robot.driveTrain);

        this.speed = speed;
        
        this.angle = angle;

        this.minDoneCycles = minDoneCycles;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Will get all inverted if called multiple times from different constructors
        leftPID = new PIDController(P, I, D, F, Robot.driveTrain.getGyroPIDSource(false), 
        		Robot.driveTrain.getDrivePIDOutput(false, true));
        
        rightPID = new PIDController(P, I, D, F, Robot.driveTrain.getGyroPIDSource(false), 
        		Robot.driveTrain.getDrivePIDOutput(true, false));
        
    	Robot.driveTrain.reset();
    	
    	
    	// See http://www.pdocs.kauailabs.com/navx-mxp/examples/rotate-to-angle-2/ on the java example @ line 73
    	leftPID.setInputRange(-180, 180.0);
    	rightPID.setInputRange(-180.0, 180.0);
    	
    	// Make input infinite
    	leftPID.setContinuous();
    	rightPID.setContinuous();
    	if(speed > 0) {
    	// Set output speed range
    	leftPID.setOutputRange(-speed, speed);
    	rightPID.setOutputRange(-speed, speed);
    	} else {
    		leftPID.setOutputRange(speed, -speed);
        	rightPID.setOutputRange(speed, -speed);
    	}
    	// Will accept within 1 degrees of target
    	leftPID.setAbsoluteTolerance(4);
    	rightPID.setAbsoluteTolerance(4);
    	
    	leftPID.setSetpoint(angle);
    	rightPID.setSetpoint(angle);
    	
    	// Start going to location
    	leftPID.enable();
    	rightPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(Robot.driveTrain.getPIDOutput(true), Robot.driveTrain.getPIDOutput(false));
    	
    	// TODO: Use WPI onTarget()
    	onTarget();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(this.doneCount > this.minDoneCycles)
    		return true;
    	else
    		return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Disable PID output and stop robot to be safe
    	leftPID.disable();
    	rightPID.disable();
    	
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
    
    private boolean onTarget() {
    	if(leftPID.getError() < 4.0 && rightPID.getError() < 4.0) {
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
    public void Turn() {
    	float slowSpeed = 0.2f;
    	float medSpeed = 0.4f;
    	if(Robot.driveTrain.getGyroPIDSource(false).pidGet() - angle < 20 && Robot.driveTrain.getGyroPIDSource(false).pidGet() - angle >= 0) {
    		Robot.driveTrain.drive(-slowSpeed,slowSpeed);
    	}
    	else if(Robot.driveTrain.getGyroPIDSource(false).pidGet() - angle < 80 && Robot.driveTrain.getGyroPIDSource(false).pidGet() - angle >= 21) {
    		Robot.driveTrain.drive(-medSpeed,medSpeed);
    	}
    }
}
