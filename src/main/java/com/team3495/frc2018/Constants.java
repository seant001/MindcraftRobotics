package com.team3495.frc2018;

public class Constants { // MODIFY BASED ON OUR ROBOT
    public static class Arm 
    {
        public static final double kMaxVelocity = 1.0;    // ft / s    // mark your units wherever they aren't intensely obvious
        public static final double kMaxHeight = 8.0;    // ft
        public static final double kMinHeight = 0.0;    // ft
        public static final int kTicksPerFoot = 1234;    // you don't need to specify units here because they're literally in the name (encoder ticks / foot)
    }
    public static class Drivetrain 
    {
        public static final double kWheelBase = 3.0;    // ft    // try as hard as you can to keep your units consistent: if you use feet, use feet, not inches
        public static final double kMaxVoltage = 12.0;
    
        public static class Left
     {
            public static class Forward
         {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement

         }
         public static class Reverse
            {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            }
    }
        public static class Right
        {
            public static class Forward
            {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            
            }
             public static class Reverse
             {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            
            }


    }
}
    public static class ControlAxes
    {
        public static final int xAxis = 0;
        public static final int yAxis = 1;
    }
    public static class Intake
    {
        public static final double kMaxVoltage =  12.0;
        public static final double kIntaking = 2.0;
        public static final double kOuttaking = -5.0;

    }
    public static class TeleThreeJoysticks
    {
        public static class Buttons
    {
        public static final int intakeOut = 4;
        public static final int intakeIn = 5;
    }
        public static final class Deadbands
        {
            public static class Left
            {
                public static final double kMinForward = .2;
                public static final double kMinReverse = -.2;
            }
            public static class Right
            {
                public static final double kMinForward = .2;
                public static final double kMinReverse = -.2;
            }
        }
    }
}