package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticMechanism extends Subsystem {

	private DoubleSolenoid sol;
	private Compressor c;
	
	
	public PneumaticMechanism() {
		super();
		
		sol = new DoubleSolenoid(RobotMap.SOLENOID_SHOOT1, RobotMap.SOLENOID_SHOOT2);
		c = new Compressor(RobotMap.COMPRESSOR_SHOOT);
		
		c.start();
		c.setClosedLoopControl(true);
	}
	
	/*
	 * Method that turns the pneumatic either on or off
	 * 
	 *  @param position true = shoot, false means holding
	 */
	public void platformPneumaticsForward() {
		sol.set(DoubleSolenoid.Value.kForward);
	}
	
	public void platformPneumaticsReverse() {
		sol.set(DoubleSolenoid.Value.kReverse);
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void platformPneumaticsEnd() {
		sol.set(DoubleSolenoid.Value.kOff);
	}

	public DoubleSolenoid.Value getPneumaticPosition() {
		return sol.get();
	}
}
