package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;

public class Intake
{
    private TalonSRX intake_left;
    private TalonSRX intake_right;
    public enum State {
        INTAKING,
        HOLDING,
        OUTTAKING,
        IDLE
    }
    private State state;

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
            state = State.IDLE;
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
       public void requestState(State state)
       {
        this.state = state;
        switch(this.state){
            case INTAKING: sendInputVolts(Constants.Intake.kIntaking);
            break;
            case OUTTAKING: sendInputVolts(Constants.Intake.kOuttaking);
            break;
            case HOLDING: sendInputVolts(Constants.Intake.kHolding);
            break;
            case IDLE:
            default: sendInputVolts(0.0);
        }
       }
    
    
}