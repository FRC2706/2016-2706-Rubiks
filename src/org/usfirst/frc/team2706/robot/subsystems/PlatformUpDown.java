package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ChangePlatformPosition;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/*
 * Subsystem that allows the robot to change the position of the 
 * platform that holds the ball; and intakes, and shoots the ball
 */
public class PlatformUpDown extends Subsystem {
	
	private Compressor c;
	private Solenoid sol;
	
	public PlatformUpDown() {
		c = new Compressor(RobotMap.COMPRESSOR_PLATFORM);
		sol = new Solenoid(RobotMap.SOLENOID_PLATFORM);
		
		c.start();
		c.setClosedLoopControl(true);
		
		LiveWindow.addActuator("PlatformUpDown", "compressor", c);
		LiveWindow.addActuator("PlatformUpDown", "pneumatic", sol);
	}
	
	// Allows one to change the position without specifying the position one wants
	public void changePosition() {
		if(sol.get() == true)
			sol.set(false);
		else if(sol.get() == false)
			sol.set(true);
	}
	
	// Allows one to enter which position they want the platform to be in
	public void changePosition(boolean position) {
		if(sol.get() != position)
			sol.set(position);
	}
	
	public boolean getPlatformPosition() {
		return sol.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ChangePlatformPosition(true));
	}

}
