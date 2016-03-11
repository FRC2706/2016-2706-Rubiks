package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ChangePlatformPosition;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * Subsytem that controls the pneumatics and compressor that 
 * move the platform up and down.
 */
public class PlatformMechanism extends Subsystem {

	private DoubleSolenoid sol;
	private DoubleSolenoid floating;
	
	
	public PlatformMechanism() {
		super();
		
		sol = new DoubleSolenoid(RobotMap.SOLENOID_PLATFORM0A, RobotMap.SOLENOID_PLATFORM0B);
		floating = new DoubleSolenoid(RobotMap.SOLENOID_PLATFORM1A, RobotMap.SOLENOID_PLATFORM1B);
	}
	
	/*
	 * Changes the position of the platform based on user input
	 */
	public void changePosition(boolean position) {
		if(position)
			sol.set(DoubleSolenoid.Value.kForward);
		else if(!position)
			sol.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void changeFinished() {
		sol.set(DoubleSolenoid.Value.kOff);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ChangePlatformPosition(true));
	}

}
