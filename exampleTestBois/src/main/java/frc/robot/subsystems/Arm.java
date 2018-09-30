package frc.robot.subsystems;

public class Arm
{
    private static Arm instance = null;

    public static Arm getInstance() {
        if(instance == null) instance = new Arm();
        return instance;
    }

        private Arm() {}
    
}