package org.usfirst.frc.team2706.robot.commands.autonomous;

import org.usfirst.frc.team2706.robot.commands.RotateDriveWithGyro;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;

import edu.wpi.first.wpilibj.command.Command;

public class BreachTurnShootWithGyroAutonomous2 extends Command {
	
	Command first;
	Command second;
	
	public BreachTurnShootWithGyroAutonomous2 (){}
	
	@Override
	protected void initialize() {
		first = new StraightDriveWithEncoders(0.85, 15, 100);
		second = new RotateDriveWithGyro(0.85, 37.5, 100);
		
		first.start();
		
		//System.out.println(first.isRunning());
	}

	boolean second1;
	
	@Override
	protected void execute() {
		if(((StraightDriveWithTime)first).done) {
			if(!second1) {
				second.start(); 
				System.out.println("TEst: Camera Start Called");
				second1 = !second1;
			}
			
			//System.out.println("Second!");		
		}
		else;
		//	System.out.println("First!");
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	
}