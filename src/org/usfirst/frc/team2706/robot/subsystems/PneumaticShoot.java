package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class PneumaticShoot extends Subsystem {

	private Compressor compress;
	private Solenoid pneu;
	
	public PneumaticShoot() {

		compress = new Compressor(RobotMap.COMPRESSOR_SHOOT);
		pneu = new Solenoid(RobotMap.SOLENOID_SHOOT);
		compress.start();
		compress.setClosedLoopControl(true);
		
		// @TODO is this the right way to set up the Live window for the pneumatics?
		LiveWindow.addActuator("ShootIntakeMechanism", "Pneumatic Punch", pneu);
		LiveWindow.addActuator("ShootIntakeMechanism", "Compressor", compress);
	}
	
	// pneumatic punch that hits the ball after the motors are on
	public void pneumaticPunch() {
		pneu.set(true);
	}
	public void endPneumatic() {
		pneu.set(false);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
