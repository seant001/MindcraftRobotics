package com.team3495.frc2018.subsystems;

public class Arm
{
    private static Arm instance = null;

    public static Arm getInstance() {
        if(instance == null) instance = new Arm();
        return instance;
    }

        private Arm() {}
    
}