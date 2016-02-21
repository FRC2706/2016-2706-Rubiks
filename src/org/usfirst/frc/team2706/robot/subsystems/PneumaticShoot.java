package org.usfirst.frc.team2706.robot.subsystems;

import org.usfirst.frc.team2706.robot.RobotMap;
import org.usfirst.frc.team2706.robot.commands.ShootBallPneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/*
 * Subsystem that turns on and off the pneumatic mechanism
 * that pushes the ball forward to be shot out by the motors
 */
public class PneumaticShoot extends Subsystem {

	private Compressor compress;
	private Solenoid pneu;
	
	public PneumaticShoot() {

		compress = new Compressor(RobotMap.COMPRESSOR_SHOOT);
		pneu = new Solenoid(RobotMap.SOLENOID_SHOOT);
		compress.start();
		compress.setClosedLoopControl(true);
		
		// TODO is this the right way to set up the Live window for the pneumatics?
		LiveWindow.addActuator("ShootIntakeMechanism", "PneumaticPunch", pneu);
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
		setDefaultCommand(new ShootBallPneumatics());
	}

}
