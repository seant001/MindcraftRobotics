package com.team3495.frc2018.controlsystem;

import com.team3495.frc2018.subsystems.Arm;
import com.team3495.frc2018.subsystems.Intake;
import com.team3495.frc2018.subsystems.Drivetrain;

public class RoboSystem {

    public Arm arm;
    public Drivetrain drivetrain;
    public Intake intake;

    private RoboSystem() {
        arm = new Arm();
        drivetrain = new Drivetrain();
        intake = new Intake();
    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}