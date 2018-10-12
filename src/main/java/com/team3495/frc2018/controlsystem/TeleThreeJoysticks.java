package com.team3495.frc2018.controlsystem;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Arm;
import com.team3495.frc2018.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import com.team3495.frc2018.Ports;
import com.team3495.frc2018.Util;
import com.team3495.frc2018.Constants;

public class TeleThreeJoysticks
{
    public RoboSystem robosystem;
    public Joystick driverLeft;
    public Joystick driverRight;
    public Joystick coDriver;

    private TeleThreeJoysticks()
    {
        robosystem = RoboSystem.getInstance();
        driverLeft = new Joystick(Ports.JOYSTICK_LEFT);
        driverRight = new Joystick(Ports.JOYSTICK_RIGHT);
        coDriver = new Joystick(Ports.JOYSTICK_CODRIVER);
    } 
    private void driver()
    {
        double left = Util.deadbandAndBound(driverLeft.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TeleThreeJoysticks.Deadbands.Left.kMinReverse, 
        Constants.TeleThreeJoysticks.Deadbands.Left.kMinForward,
        -1.0, 1.0);
        double right = Util.deadbandAndBound(driverRight.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TeleThreeJoysticks.Deadbands.Right.kMinReverse, 
        Constants.TeleThreeJoysticks.Deadbands.Right.kMinForward,
        -1.0, 1.0);

        robosystem.drivetrain.sendInputNormalized(left, right);
   
   
    }
    private void coDriver()
    {
        if(coDriver.getRawButton(Constants.TeleThreeJoysticks.Buttons.intakeIn))
        {
            robosystem.intake.requestState(Intake.State.INTAKING);
        }else if(coDriver.getRawButton(Constants.TeleThreeJoysticks.Buttons.intakeOut))
        {
            robosystem.intake.requestState(Intake.State.OUTTAKING);
        }else
        {
            robosystem.intake.requestState(Intake.State.IDLE);
        }
        if(coDriver.getRawButton(Constants.TeleThreeJoysticks.Buttons.armRaise))
        {
            robosystem.arm.requestState(Arm.State.GOING_UP);
        }else if (coDriver.getRawButton(Constants.TeleThreeJoysticks.Buttons.armLower))
        {
            robosystem.arm.requestState(Arm.State.GOING_DOWN);
        }else {
            robosystem.arm.requestState(Arm.State.HOLDING);
        }
    }
    public void update()
    {
        driver();
        coDriver();
    }

    private static TeleThreeJoysticks instance = null;

    public static TeleThreeJoysticks getInstance() {
        if(instance == null) instance = new TeleThreeJoysticks();
        return instance;

}
}