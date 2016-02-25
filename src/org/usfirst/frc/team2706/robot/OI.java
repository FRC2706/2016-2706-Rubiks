package org.usfirst.frc.team2706.robot;


import org.usfirst.frc.team2706.robot.commands.ChangePlatformPosition;
import org.usfirst.frc.team2706.robot.commands.IntakeBall;
import org.usfirst.frc.team2706.robot.commands.PassBall;
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
	
	// TODO: Arcade drive joystick (Backwards)
	Joystick stick = new Joystick(0);
	
	
	// TODO get the actual map of the buttons
	// button to do with the platform motors
	Button buttonShoot = new JoystickButton(stick, 1);
	Button buttonPass = new JoystickButton(stick, 2);
	Button buttonIntake = new JoystickButton(stick, 3);
	
	// buttons to change the platform position
	Button buttonPlatformUp = new JoystickButton(stick, 4);
	Button buttonPlatformDown = new JoystickButton(stick, 5);
	
	
    public Joystick getJoystick() {
        return stick;
    }
    
    public OI() {
    	// While the buttons are held constantly run the command
    	buttonShoot.whileHeld(new ShootBall(0.5));
    	buttonPass.whileHeld(new PassBall(0.5));
    	buttonIntake.whileHeld(new IntakeBall(0.5));
    	
    	// Depending on which button is pressed down the platform changes
    	// to that position
    	buttonPlatformUp.whenPressed(new ChangePlatformPosition(true));
    	buttonPlatformDown.whenPressed(new ChangePlatformPosition(false));
    	
    }
}

