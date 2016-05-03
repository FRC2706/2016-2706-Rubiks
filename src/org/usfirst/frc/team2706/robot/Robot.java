
package org.usfirst.frc.team2706.robot;

import org.usfirst.frc.team2706.robot.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team2706.robot.commands.AutomaticCameraControl;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithEncoders;
import org.usfirst.frc.team2706.robot.commands.StraightDriveWithTime;
import org.usfirst.frc.team2706.robot.commands.TeleopPneumaticControl;
import org.usfirst.frc.team2706.robot.commands.autonomous.BreachGoToTargetShootGyroAutonomous;
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
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the IterativeRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the manifest file in the
 * resource directory.
 */
public class Robot extends IterativeRobot {

    public static Camera camera;
    public static DriveTrain driveTrain;
    public static AutonomousSelector hardwareChooser;
    public static OI oi;

    Command autonomousCommand;
    AutomaticCameraControl cameraCommand;
    TeleopPneumaticControl teleopControl;

    public static Solenoid ringLightPower;
    public static DoubleSolenoid ballKicker;
    public static DoubleSolenoid armCylinder1;
    public static DoubleSolenoid armCylinder2;
    public static CANTalon intakeLeft;
    public static CANTalon intakeRight;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    public void robotInit() {
        oi = new OI();

        // Instantiate the robot subsystems
        driveTrain = new DriveTrain();
        camera = new Camera(Camera.CAMERA_IP);

        // Set up our autonomous modes with the hardware selector switch
        // holy long class names batman
        hardwareChooser = new AutonomousSelector(
                        /* no switch: do nothing */ new ArcadeDriveWithJoystick(),
                        /* position 1: do nothing */ new ArcadeDriveWithJoystick(),
                        /* position 2: low goal gyro */ new BreachGoToTargetShootGyroAutonomous(),
                        /* position 3: low goal camera */ new StraightDriveWithEncoders(0.5, 6, 25),
                        /* position 4: low goal hybrid */ new BreachGoToTargetShootHybridAutonomous(),
                        /* position 5: reach anything */ new StraightDriveWithEncoders(0.5, 6, 25),
                        /* position 6: breach slow */ new StraightDriveWithEncoders(0.7, 15, 25),
                        /* position 7: breach fast */ new StraightDriveWithEncoders(0.85, 15, 25),
                        /* position 8: portcullis */ new PortCullisBreachPlay(),
                        /* position 9: cheval de frise */ new ChevalDeFriseBreachPlay(),
                        /* position 10: high goal */ new BreachGoToTargetShootHybridAutonomousHighGoal());
                        /* position 11 - 12 currently unused */

        // TODO: we should move these to subsystem classes
        // and also use the RobotMap values
        ballKicker = new DoubleSolenoid(0, 1);
        armCylinder1 = new DoubleSolenoid(2, 3);
        armCylinder2 = new DoubleSolenoid(4, 5);
        teleopControl = new TeleopPneumaticControl();
        intakeLeft = new CANTalon(RobotMap.CAN_INTAKE_LEFT);
        intakeRight = new CANTalon(RobotMap.CAN_INTAKE_RIGHT);

        // Set up the Microsoft LifeCam and start streaming it to the Driver Station
        CameraServer server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");

        // Turn on the ring light for vision tracking
        ringLightPower = new Solenoid(RobotMap.RING_LIGHT);
        ringLightPower.set(true);

        cameraCommand = new AutomaticCameraControl();
    }

    /**
     * This function is called once each time the robot enters Disabled mode. You can use it to
     * reset any subsystem information you want to clear when the robot is disabled.
     */
    public void disabledInit() {
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
        cameraCommand.start();
        driveTrain.resetGyro();

        System.out.println(hardwareChooser.getSelected());
        autonomousCommand = hardwareChooser.getSelected();

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
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
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        // cameraCommand.start();
        // cameraCommand.cancel(); // Uncomment/comment to disable/enable camera movement
        Robot.camera.resetCamera();
        teleopControl.start();


    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Robot.camera.robotTurnDegrees();
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
