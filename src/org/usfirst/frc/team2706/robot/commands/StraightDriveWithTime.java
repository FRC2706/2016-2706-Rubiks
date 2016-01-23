package org.usfirst.frc.team2706.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive certain amount of time
 */
public class StraightDriveWithTime extends Command {
    
	private final double speed;
	
	private final long time;
	
	/**
	 * Drive at a specific speed for a certain amount of time
	 * 
	 * @param leftSpeed Speed in range [-1,1]
	 * @param rightSpeed Speed in range [-1,1]
	 * @param time Time in milliseconds to drive
	 */
    public StraightDriveWithTime(double speed, long time) {
        requires(Robot.driveTrain);

        this.speed = speed;
        
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	StraightDriveWithTime ref = this;
    	
    	// TimerTask to end command
    	TimerTask interrupt = new TimerTask() {
			@Override
			public void run() {
				ref.cancel();
			}
    	};
    	
    	Timer t = new Timer();
    	
    	// End command after time
    	t.schedule(interrupt, time);
    	
    	// Drive while command is running
    	Robot.driveTrain.drive(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted by timer
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
