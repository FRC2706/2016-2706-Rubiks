package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PlatformUpDown extends Subsystem {

	// true is up, false is down
	private boolean position;
	
	private Compressor c;
	private Solenoid sol;
	
	public PlatformUpDown() {
		c = new Compressor(RobotMap.COMPRESSOR_PLATFORM);
		sol = new Solenoid(RobotMap.SOLENOID_PLATFORM);
		c.
	}
	
	public void changePosition() {
		if(position == true)
			sol.set(false);
		else if(position == false)
			sol.set(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}