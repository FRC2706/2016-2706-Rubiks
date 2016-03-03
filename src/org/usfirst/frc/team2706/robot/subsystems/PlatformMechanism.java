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

	//private Compressor c;
	private DoubleSolenoid sol;
	private DoubleSolenoid floating;
	
	
	public PlatformMechanism() {
		super();
		
		// Kevin Lam: We don't actually need to do this - creating a DoubleSolenoid
		//            automatically sets up the compressor control system under the hood.
		//c = new Compressor(RobotMap.COMPRESSOR_PLATFORM);
		sol = new DoubleSolenoid(RobotMap.SOLENOID_PLATFORM0A, RobotMap.SOLENOID_PLATFORM0B);
		floating = new DoubleSolenoid(RobotMap.SOLENOID_PLATFORM1A, RobotMap.SOLENOID_PLATFORM1B);
		
		//c.start();
		//c.setClosedLoopControl(true);
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
