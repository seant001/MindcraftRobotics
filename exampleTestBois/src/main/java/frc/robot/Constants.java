public class Constants { //MODIFY BASED ON OUR ROBOT
    public static class Arm {
        public static final double kMaxVelocity = 1.0;    // ft / s    // mark your units wherever they aren't intensely obvious
        public static final double kMaxHeight = 8.0;    // ft
        public static final double kMinHeight = 0.0;    // ft
        public static final int kTicksPerFoot = 1234;    // you don't need to specify units here because they're literally in the name (encoder ticks / foot)
    }
    public static class Drivetrain {
        public static final double kWheelBase = 3.0;    // ft    // try as hard as you can to keep your units consistent: if you use feet, use feet, not inches
        // ...
    }
    // ...
}