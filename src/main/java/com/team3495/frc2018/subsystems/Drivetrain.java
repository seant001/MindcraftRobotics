package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain
{
    //talons, subsystems, etc.
    private TalonSRX drivetrain_leftMaster;
    private TalonSRX drivetrain_leftSlave;
    private TalonSRX drivetrain_rightMaster;
    private TalonSRX drivetrain_rightSlave;
    private Gyro gyro;
    public Odometry odometry;

    //enums/state variables

    //constructors
    private Drivetrain()
    {
        drivetrain_leftMaster = new TalonSRX(Ports.DRIVETRAIN_LEFT_MASTER);
        drivetrain_leftSlave = new TalonSRX(Ports.DRIVETRAIN_LEFT_SLAVE);
        drivetrain_rightMaster = new TalonSRX(Ports.DRIVETRAIN_RIGHT_MASTER);
        drivetrain_rightSlave = new TalonSRX(Ports.DRIVETRAIN_RIGHT_SLAVE);

        drivetrain_leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        drivetrain_rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        
        drivetrain_rightMaster.getSensorCollection().setQuadraturePosition(0, 10);
        drivetrain_leftMaster.getSensorCollection().setQuadraturePosition(0, 10);
        drivetrain_leftMaster.setSensorPhase(true);//cry and add negative


        gyro = new ADXRS450_Gyro();
        gyro.reset();

        odometry = new Odometry(0,0,0);

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
    public class Odometry
    {
        double x;
        double y;
        double heading;
        public void Update()
        {
            double leftVelocity = drivetrain_leftMaster.getSensorCollection().getQuadratureVelocity();
            double rightVelocity = drivetrain_rightMaster.getSensorCollection().getQuadratureVelocity();
            double velocity = (leftVelocity+rightVelocity)/2.0;
            double heading = Math.toRadians(gyro.getAngle());
            double dx = velocity * Math.cos(heading) * Constants.kLoopTime;
            double dy = velocity * Math.sin(heading) * Constants.kLoopTime;
            this.x += dx;
            this.y += dy;
            this.heading = heading;

            SmartDashboard.putNumber("X", x);
            SmartDashboard.putNumber("Y", y);
            SmartDashboard.putNumber("Heading", Math.toDegrees(heading));
            SmartDashboard.putNumber("Left Position", -drivetrain_leftMaster.getSensorCollection().getQuadraturePosition());
            SmartDashboard.putNumber("Right Position", drivetrain_rightMaster.getSensorCollection().getQuadraturePosition());
            SmartDashboard.putNumber("Left Position", -drivetrain_leftMaster.getSensorCollection().getQuadratureVelocity());
            SmartDashboard.putNumber("Right Position", drivetrain_rightMaster.getSensorCollection().getQuadratureVelocity());

            
        }
        public Odometry(double x, double y, double heading)
        {
            this.x  = x;
            this.y = y;
            this.heading = heading;
        }
    }

}