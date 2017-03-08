
package org.usfirst.frc.team2706.robot;

import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static OI oi;

    TeleopPneumaticControl teleopControl;
	
    public static DoubleSolenoid ballKicker;
    public static DoubleSolenoid armCylinder1;
    public static DoubleSolenoid armCylinder2;
    public static CANTalon intakeLeft;
    public static CANTalon intakeRight;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		// Instantiate the robot subsystems
        driveTrain = new DriveTrain();      
        
        // TODO: we should move these to subsystem classes
        // and also use the RobotMap values
        ballKicker = new DoubleSolenoid(0,1);
        armCylinder1 = new DoubleSolenoid(2,3);
        armCylinder2 = new DoubleSolenoid(4,5);
        teleopControl = new TeleopPneumaticControl();
        intakeLeft = new CANTalon(RobotMap.CAN_INTAKE_LEFT);
        intakeRight = new CANTalon(RobotMap.CAN_INTAKE_RIGHT);

		// Set up the Microsoft LifeCam and start streaming it to the Driver Station
		CameraServer.getInstance().startAutomaticCapture();	
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	teleopControl.cancel();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * Initialize autonomous mode using the hardware chooser
	 */
    public void autonomousInit() {
    	driveTrain.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
        teleopControl.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
    private void log() {
        driveTrain.log();
    }
}
