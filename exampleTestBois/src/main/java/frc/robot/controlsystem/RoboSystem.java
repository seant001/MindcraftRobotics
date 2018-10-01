package frc.robot.controlsystem;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drivetrain;

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