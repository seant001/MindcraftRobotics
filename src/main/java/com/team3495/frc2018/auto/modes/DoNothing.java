package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;

public class DoNothing extends AutoModeBase 
{
    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
       
    }
}