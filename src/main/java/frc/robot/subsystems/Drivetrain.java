package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
import frc.robot.Ports;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain
{
    //talons, subsystems, etc
    private TalonSRX drivetrain_leftMaster;
    private TalonSRX drivetrain_leftSlave;
    private TalonSRX drivetrain_rightMaster;
    private TalonSRX drivetrain_rightSlave;

    //enums/state variables


    //constructors
    private Drivetrain()
    {
        drivetrain_leftMaster = new TalonSRX(Ports.DRIVETRAIN_LEFT_MASTER);
        drivetrain_leftSlave = new TalonSRX(Ports.DRIVETRAIN_LEFT_SLAVE);
        drivetrain_rightMaster = new TalonSRX(Ports.DRIVETRAIN_RIGHT_MASTER);
        drivetrain_rightSlave = new TalonSRX(Ports.DRIVETRAIN_RIGHT_SLAVE);

        drivetrain_rightSlave.follow(drivetrain_rightMaster);
        drivetrain_leftSlave.follow(drivetrain_leftMaster);

        drivetrain_rightMaster.configVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_rightMaster.enableVoltageCompensation(true);
        drivetrain_rightMaster.setInverted(true);
        drivetrain_rightSlave.setInverted(true);
        drivetrain_leftMaster.configVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_leftMaster.enableVoltageCompensation(true);
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
     public void setPower(double left, double right)
    {
     drivetrain_leftMaster.set(ControlMode.PercentOutput, left);
     drivetrain_rightMaster.set(ControlMode.PercentOutput, right);
    }

   public void sendInputVolts(double leftVoltage, double rightVoltage)
   {
      drivetrain_leftMaster.set(ControlMode.PercentOutput, leftVoltage/Constants.Drivetrain.kMaxVoltage);
		drivetrain_rightMaster.set(ControlMode.PercentOutput, rightVoltage/Constants.Drivetrain.kMaxVoltage);
    }
    
    public void sendInputNyooms(double left, double right)
    {
     double leftVolts;
     double rightVolts;
     
     leftVolts = (left > 0) ?
        Constants.Drivetrain.Left.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Left.Reverse.kStaticFrictionFeedForward;
     leftVolts += left * ((left > 0) ?
        Constants.Drivetrain.Left.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Left.Reverse.kVoltsPerFootPerSecond);
     rightVolts = (right > 0) ?
        Constants.Drivetrain.Right.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Right.Reverse.kStaticFrictionFeedForward;
     rightVolts += right * ((right > 0) ?
        Constants.Drivetrain.Right.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Right.Reverse.kVoltsPerFootPerSecond);

     sendInputVolts(leftVolts, rightVolts);
    }
    
    
    
    
    public void limelightTurning(double leftCommand, double rightCommand)
    {
      double rightVolts = 0;
      double leftVolts = 0;

      float heading_error = (float)-Limelight.getTx();
      float steering_adjust = 0.0f;
      if (Limelight.getTx()> 1.0)
      {
      steering_adjust = Constants.Drivetrain.Kp*heading_error - Constants.Drivetrain.min_command;
    }
   else if (Limelight.getTx() < 1.0) 
      {
      steering_adjust = Constants.Drivetrain.Kp*heading_error +Constants.Drivetrain.min_command;
      }
      leftVolts += (steering_adjust + leftCommand);
      rightVolts -= (steering_adjust + rightCommand);

      drivetrain_leftMaster.set(ControlMode.PercentOutput, leftVolts/Constants.Drivetrain.kMaxVoltage);
		drivetrain_rightMaster.set(ControlMode.PercentOutput, rightVolts/Constants.Drivetrain.kMaxVoltage);
    }

   
   
    public void update()
    {

    }
}