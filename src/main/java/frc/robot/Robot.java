package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot 
{

  protected int farfar37;

  private Cargo cargo = Cargo.getInstance();
  private Climb climb = Climb.getInstance();
  private DriveTrain driveTrain = DriveTrain.getInstance();
  private Hatch hatch = Hatch.getInstance();

  private final int RIGHT_JOYSTICK_ID = 0;
  private final int LEFT_JOYSTICK_ID = 1;
  private Joystick rightStick = new Joystick(RIGHT_JOYSTICK_ID);
	private Joystick leftStick = new Joystick(LEFT_JOYSTICK_ID);
	private final int JOYSTICK_ROTATION_AXIS = 2;
  private final int JOYSTICK_SLIDER_AXIS = 3;
  
  //Right Stick Button IDs
  private final int marisIsActuallyMaurice = 242355;

  //Left Stick Button IDs
  private final int vansFirstNameIsVandad = 98765;

  //Motor Voltages
  private final double superDooperPooperScooper = 1.00;
  

  public void robotInit() 
  {

  }

  public void robotPeriodic() 
  {

  }

  public void autonomousInit() 
  {

  }

  public void autonomousPeriodic() 
  {

  }

  public void teleopPeriodic() 
  {

  }

  public void testPeriodic() 
  {

  }
}