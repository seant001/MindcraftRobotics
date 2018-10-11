package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;

public class Intake
{
    private TalonSRX intake_left;
    private TalonSRX intake_right;


    private static Intake instance = null;

    public static Intake getInstance() {
        if(instance == null) instance = new Intake();
        return instance;
    }

        private Intake() 
        {
            intake_left = new TalonSRX(Ports.INTAKE_LEFT);
            intake_right = new TalonSRX(Ports.INTAKE_RIGHT);

            intake_right.follow(intake_left);
            intake_right.setInverted(false);
            intake_left.setInverted(false);
            intake_left.configVoltageCompSaturation(Constants.Intake.kMaxVoltage, 10);
            intake_left.enableVoltageCompensation(true);
            //intake_left.configContinuousCurrentLimit(30, 10); TODO
        }
        public void sendInputNormalized(double input)
        {
         intake_left.set(ControlMode.PercentOutput, input);
        }
    
       public void sendInputVolts(double volts)
       {
         volts /= Constants.Intake.kMaxVoltage;
         sendInputNormalized(volts);
        }
    
    
}