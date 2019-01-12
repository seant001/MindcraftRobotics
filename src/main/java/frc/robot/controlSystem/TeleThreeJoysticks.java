package frc.robot.controlSystem;

import frc.robot.controlSystem.RoboSystem;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Ports;
import frc.robot.Util;
import frc.robot.Constants;

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
        double left = Util.deadbandAndBound(-driverLeft.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Left.kMinReverse, 
        Constants.TankDriver.Deadbands.Left.kMinForward,
        -1.0, 1.0);
        double right = Util.deadbandAndBound(-driverRight.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Right.kMinReverse, 
        Constants.TankDriver.Deadbands.Right.kMinForward,
        -1.0, 1.0);

        robosystem.drivetrain.setPower(left, right);

        if(driverRight.getRawButton(1))
        {
        robosystem.drivetrain.limelightTurning(0.25, 0.25);
    }

        
   

   
    }
    private void coDriver()
    {
        /*if(coDriver.getRawButton(Constants.JoystickCodriver.Buttons.intakeIn))
        {
            robosystem.intake.requestState(Intake.RollerState.INTAKING);
        }else if(coDriver.getRawButton(Constants.JoystickCodriver.Buttons.intakeOut))
        {
            robosystem.intake.requestState(Intake.RollerState.OUTTAKING);
        }else
        {
            robosystem.intake.requestState(Intake.RollerState.IDLE);
        }*/
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