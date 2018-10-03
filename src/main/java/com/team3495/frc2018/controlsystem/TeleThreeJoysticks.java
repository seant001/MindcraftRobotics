package com.team3495.frc2018.controlsystem;

import com.team3495.frc2018.controlsystem.RoboSystem;
import edu.wpi.first.wpilibj.Joystick;

public class TeleThreeJoysticks
{
    public RoboSystem robosystem;
    public Joystick driverLeft;
    public Joystick driverRight;
    public Joystick coDriver;

    private TeleThreeJoysticks()
    {
        robosystem = new RoboSystem();
        driverLeft = new Joystick();
        driverRight = new Joystick();
        coDriver = new Joystick();
    } 
    driver()
    {
        robosystem.drivetrain.sendInputNormalized(driverLeft.getAxis(), driverRight.getAxis());
    }
    coDriver()
    {


    }

    private static TeleThreeJoysticks instance = null;

    public static TeleThreeJoysticks getInstance() {
        if(instance == null) instance = new TeleThreeJoysticks();
        return instance;

}