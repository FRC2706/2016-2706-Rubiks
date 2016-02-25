package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ChangePlatformPosition;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * Subsytem that controls the pneumatics and compressor that 
 * move the platform up and down.
 */
public class PlatformMechanism extends Subsystem {

	private Compressor c;
	private Solenoid sol;
	
	
	public PlatformMechanism() {
		super();
		
		c = new Compressor(RobotMap.COMPRESSOR_PLATFORM);
		sol = new Solenoid(RobotMap.SOLENOID_PLATFORM);
		
		c.start();
		c.setClosedLoopControl(true);
	}
	
	/*
	 * Method that changes the position that the platform is in,
	 * eg. if it was up, then it will go down
	 */
	public void changePosition() {
		if(sol.get() == true)
			sol.set(false);
		else if(sol.get() == false)
			sol.set(true);
	}
	
	/*
	 * Changes the position of the platform based on user input
	 */
	public void changePosition(boolean position) {
		if(sol.get() != position)
			sol.set(position);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ChangePlatformPosition(true));
	}

}
