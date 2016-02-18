package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/*
 * When the button is pressed the motors that shoot the
 * ball start spinning, then the user can press the buttom
 * to shoot the ball. If the button is pressed again, the 
 * motors stop spinning.
 */
public class ShootBallMotors extends Command {

	private double speed;
	
	public ShootBallMotors(double speed) {
		super();
		
		// requires, etc. code
		this.speed = speed;
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Robot.platformMotors.shoot(speed);
		

	}
	
	protected boolean isFinished() {
		// TODO return whether or not the ball has been shot out
		// or when the person presses the button
		return false;
	}

	

	@Override
	protected void interrupted() {
		// TODO method should be interrupted if the button is pressed again
		end();
	}

	@Override
	protected void end() {
		// shut off motors
		Robot.platformMotors.shoot(0);
	}
}
