package frc.robot.subsystems;

public class Intake
{
    private static Intake instance = null;

    public static Intake getInstance() {
        if(instance == null) instance = new Intake();
        return instance;
    }

        private Intake() {}
    
    
}