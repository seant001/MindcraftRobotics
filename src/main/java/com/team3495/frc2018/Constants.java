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
    }
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