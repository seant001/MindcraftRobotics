/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team3495.frc2018;

import com.team3495.frc2018.auto.AutoModeExecutor;
import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.controlsystem.TeleThreeJoysticks;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private TeleThreeJoysticks teleControllers;
  private Compressor compressor;
  private RoboSystem robosystem;
  private AutoModeExecutor pauloGarcia;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    teleControllers = TeleThreeJoysticks.getInstance();
    robosystem = RoboSystem.getInstance();
    pauloGarcia = AutoModeExecutor.getInstance();
    CameraServer.getInstance().startAutomaticCapture();
    
	  compressor = new Compressor(0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    robosystem.drivetrain.odometry.Update();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    pauloGarcia.selectAutoMode();
    pauloGarcia.start();
  }

  private static boolean autoFinished = false;
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*if (!autoFinished)
    switch (selected_auto) {
      case "Near Switch":
        // Put custom auto code here
        switch (selected_pos)
        {
          case "Left":
          //Left side same switch
          robosystem.intake.requestState(Intake.PistonState.CLOSED);
          robosystem.intake.requestState(Intake.PistonState.OPEN);
          robosystem.drivetrain.sendInputVolts(4.5, 3.5);
          Timer.delay(1.95);
          robosystem.arm.requestState(Arm.State.GOING_UP);
          Timer.delay(0.8);
          robosystem.drivetrain.sendInputVolts(0.0, 0.0);
          robosystem.arm.requestState(Arm.State.HOLDING);
          Timer.delay(1);
          if(switch_side == 'L'){
          robosystem.intake.requestState(Intake.RollerState.OUTTAKING);
          Timer.delay(2);
          robosystem.intake.requestState(Intake.RollerState.IDLE);}
          break;
          case "Right":
          //right side same switch
          robosystem.intake.requestState(Intake.PistonState.CLOSED);
          robosystem.intake.requestState(Intake.PistonState.OPEN);
          robosystem.drivetrain.sendInputVolts(3.5, 4.8);
          Timer.delay(1.60);
          robosystem.arm.requestState(Arm.State.GOING_UP);
          Timer.delay(0.8);
          robosystem.drivetrain.sendInputVolts(0.0, 0.0);
          robosystem.arm.requestState(Arm.State.HOLDING);
          Timer.delay(1);
          if(switch_side == 'R'){
          robosystem.intake.requestState(Intake.RollerState.OUTTAKING);
          Timer.delay(2);
          robosystem.intake.requestState(Intake.RollerState.IDLE);}
          break;
          default:
          break;
        }
        break;
      default:
        // Put default auto code here
        break;
    }autoFinished = true;*/
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    teleControllers.update();
  
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
