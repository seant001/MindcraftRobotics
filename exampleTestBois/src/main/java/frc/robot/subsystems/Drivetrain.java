package frc.robot.subsystems;

import frc.robot.Constants;

public class Drivetrain
{
    //talons, subsystems, etc.
    TalonSRX drivetrain_leftMaster;
    TalonSRX drivetrain_leftSlave;
    TalonSRX drivetrain_rightMaster;
    TalonSRX drivetrain_rightSlave;

    //enums/state variables

    //constructors
    private Drivetrain()
    {
        drivetrain_leftMaster = new TalonSRX(DRIVETRAIN_LEFT_MASTER);
        drivetrain_leftSlave = new TalonSRX(DRIVETRAIN_LEFT_SLAVE);
        drivetrain_rightMaster = new TalonSRX(DRIVETRAIN_RIGHT_MASTER);
        drivetrain_rightSlave = new TalonSRX(DRIVETRAIN_RIGHT_SLAVE);

        drivetrain_rightSlave.follow(drivetrain_rightMaster);
        drivetrain_leftSlave.follow(drivetrain_leftMaster);

        drivetrain_rightMaster.ConfigVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_rightMaster.EnableVoltageCompensation(true);
        drivetrain_leftMaster.ConfigVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_leftMaster.EnableVoltageCompensation(true);


    }

    //instance junk
    private static Drivetrain instance = null;

    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }
 
    //methods for controlling
        /* @param left input to left side of drivetrain, in [-1.0, 1.0]
        * @param right input to right side of drivetrain, in [-1.0, 1.0]
        */
     public void sendInputNormalized(double left, double right)
    {
     drivetrain_leftMaster.set(ControlMode.PercentOutput, left);
     drivetrain_rightMaster.set(ControlMode.PercentOutput, right);
    }

   public void sendInputVolts(double left, double right)
   {
     left /= Constants.Drivetrain.kMaxVoltage;
     right /= Constants.Drivetrain.kMaxVoltage;
     sendInputNormalized(left, right);
    }
    public static sendInputNyooms(double left, double right)
    {
     double leftVolts;
     double rightVolts;
     
     leftVolts = (left > 0) ?
        Constants.Drivetrain.Left.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Left.Reverse.kStaticFrictionFeedForward;
     leftVolts += left * (left > 0) ?
        Constants.Drivetrain.Left.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Left.Reverse.kVoltsPerFootPerSecond;
     rightVolts = (right > 0) ?
        Constants.Drivetrain.Right.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Right.Reverse.kStaticFrictionFeedForward;
     rightVolts += right * (right > 0) ?
        Constants.Drivetrain.Right.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Right.Reverse.kVoltsPerFootPerSecond;

     sendInputVolts(leftVolts, rightVolts);

    }

}