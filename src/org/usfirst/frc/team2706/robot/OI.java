package org.usfirst.frc.team2706.robot;


import org.usfirst.frc.team2706.robot.commands.ChangePlatformPosition;
import org.usfirst.frc.team2706.robot.commands.IntakeBall;
import org.usfirst.frc.team2706.robot.commands.RotateDriveWithGyro;
import org.usfirst.frc.team2706.robot.commands.ShootBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("unused")
public class OI {
    //// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	Joystick driveStick = new Joystick(0);
	Joystick controlStick = new Joystick(1);	
	
	Button controlButtonA = new JoystickButton(controlStick, 1);
	Button controlButtonB = new JoystickButton(controlStick, 2);
	Button controlButtonX = new JoystickButton(controlStick, 3);
	Button controlButtonY = new JoystickButton(controlStick, 4);
	Button controlButtonLB = new JoystickButton(controlStick, 5);            
	Button controlButtonRB = new JoystickButton(controlStick, 6);            	

	public Joystick getDriverJoystick() {
        return driveStick;
    }
    
	public Joystick getOperatorJoystick() {
        return controlStick;
    }

	public OI() {
    	// While the buttons are held constantly run these commands
		//controlButtonRB.whileHeld(new ShootBall(1.0)); // TODO: shootball command needs kicker
    	//controlButtonLB.whileHeld(new IntakeBall(0.3));
    	
    	// Depending on which button is pressed down the arm changes
    	// to that position
    	//controlButtonY.whenPressed(new ChangePlatformPosition(true)); // arm goes up
    	//controlButtonA.whenPressed(new ChangePlatformPosition(false)); // arm goes down
    	
    }
}

