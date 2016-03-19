
package org.usfirst.frc.team2706.robot;

import org.usfirst.frc.team2706.robot.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team2706.robot.commands.AutomaticCameraControl;
import org.usfirst.frc.team2706.robot.commands.ResetCameraEndAuto;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.autonomous.BreachGoToTargetShootHybridAutonomous;
import org.usfirst.frc.team2706.robot.commands.autonomous.BreachGoToTargetShootHybridAutonomousHighGoal;
import org.usfirst.frc.team2706.robot.commands.autonomous.ChevalDeFriseBreachPlay;
import org.usfirst.frc.team2706.robot.commands.autonomous.PortCullisBreachPlay;
import org.usfirst.frc.team2706.robot.subsystems.AutonomousSelector;
import org.usfirst.frc.team2706.robot.subsystems.Camera;
import org.usfirst.frc.team2706.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
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

	public static Camera camera;
	public static Solenoid ringLightPower;
	public static DriveTrain driveTrain;
	public static DoubleSolenoid solenoid;
	public static AutonomousSelector hardwareChooser;
	public static OI oi;
    Command autonomousCommand;
    TeleopPneumaticControl teleopControl;
    AutomaticCameraControl cameraCommand;
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
		CameraServer server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		ringLightPower = new Solenoid(RobotMap.RING_LIGHT);	
		ringLightPower.set(true);
        driveTrain = new DriveTrain();      

        camera = new Camera(Camera.CAMERA_IP);
        hardwareChooser = new AutonomousSelector(new ArcadeDriveWithJoystick(), new ArcadeDriveWithJoystick(),
        		new BreachGoToTargetShootHybridAutonomous(),/*TODO: camera full*/ new ArcadeDriveWithJoystick(),new ArcadeDriveWithJoystick()
        		,new StraightDriveWithTime(0.5,3),new StraightDriveWithTime(0.5,6),new StraightDriveWithTime(0.7,6),new PortCullisBreachPlay(),new ChevalDeFriseBreachPlay(),new BreachGoToTargetShootHybridAutonomousHighGoal());
        ballKicker = new DoubleSolenoid(0,1);
        armCylinder1 = new DoubleSolenoid(2,3);
        armCylinder2 = new DoubleSolenoid(4,5);
        cameraCommand = new AutomaticCameraControl();
        teleopControl = new TeleopPneumaticControl();
        intakeLeft = new CANTalon(RobotMap.CAN_INTAKE_LEFT);
        intakeRight = new CANTalon(RobotMap.CAN_INTAKE_RIGHT);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	if(!cameraCommand.isCanceled()) cameraCommand.cancel();
    	teleopControl.cancel();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	driveTrain.reset();
    	driveTrain.gyroReset();
        cameraCommand.start();
    	System.out.println(hardwareChooser.getSelected());
        autonomousCommand = hardwareChooser.getSelected();
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
       // cameraCommand.start();
        //cameraCommand.cancel(); // Uncomment/comment to disable/enable camera movement
        new ResetCameraEndAuto().start();
        teleopControl.start();
        cameraCommand.start();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Robot.camera.RobotTurnDegrees();
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