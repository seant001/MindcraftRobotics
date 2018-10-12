package com.team3495.frc2018;

public class Constants { // MODIFY BASED ON OUR ROBOT
    public static class Arm 
    {
       public static final double kMaxVoltage = 12.0;
       public static final double kSteadyStateFeedforward = 2.0;
       public static final double kRaiseVoltage = 8.0;
       public static final double kLowerVoltage = -8.0;
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
        public static final double kIntaking = 5.0;
        public static final double kOuttaking = -5.0;
        public static final double kHolding = 0.5;

    }
    public static class TeleThreeJoysticks
    {
        public static class Buttons
    {
        public static final int intakeOut = 4;
        public static final int intakeIn = 5;
        public static final int armRaise = 1;
        public static final int armLower = 2;
    }
        public static final class Deadbands
        {
            public static class Left
            {
                public static final double kMinForward = .1;
                public static final double kMinReverse = -.1;
            }
            public static class Right
            {
                public static final double kMinForward = .1;
                public static final double kMinReverse = -.1;
            }
        }
    }
}