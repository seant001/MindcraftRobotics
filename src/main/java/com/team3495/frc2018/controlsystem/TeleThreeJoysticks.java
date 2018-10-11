package com.team3495.frc2018.controlsystem;

import com.team3495.frc2018.controlsystem.RoboSystem;
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
        if(coDriver.getRawButtonPressed(Constants.TeleThreeJoysticks.Buttons.intakeIn))
        {
            robosystem.intake.sendInputVolts(Constants.Intake.kIntaking);
        }else if(coDriver.getRawButtonPressed(Constants.TeleThreeJoysticks.Buttons.intakeOut))
        {
            robosystem.intake.sendInputVolts(Constants.Intake.kOuttaking);
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