package frc.robot.controlSystem;

import frc.robot.subsystems.Drivetrain;

public class RoboSystem {

    public Drivetrain drivetrain;


    private RoboSystem() {
        drivetrain = Drivetrain.getInstance();
    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}