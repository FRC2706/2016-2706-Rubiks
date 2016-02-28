package org.usfirst.frc.team2706.robot.commands;

import org.usfirst.frc.team2706.robot.Robot;
import org.usfirst.frc.team2706.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
//Movement of Driver
public class CameraFreeLook extends Command {
	//System Variables - No point touching
	private float GamePadX = 0;
	private float GamePadY = 0;
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		TurnOnGamepad(GamePadX,GamePadY);
	}

	@Override
	protected void initialize() {
		System.out.println("Camera FreeLook Mode Initialized");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	public void TurnOnGamepad(float RawGamepadX, float RawGamepadY) {
				float val1 = (float) ((float)((int)(RawGamepadX * 100)) / 100);
				float val2 = (float) ((float)((int)(RawGamepadY * 100)) / 100);
				val1 *= val1;
				val2 *= val2;
				if(RawGamepadX > 0) {
					val1 *= -1;
				}
				if(RawGamepadY > 0) {
					val2 *= -1;
				}
				val1 /= 10;
				val2 /= 10;
				Robot.camera.ProtectedSetServoAngles(val1,val2);
			}
}
