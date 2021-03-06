package org.firstinspires.ftc.teamcode.util;

/**
 * Created by O on 10/28/2017.
 */
public class Constants {
    public class Drivetrain {
        public static final double HIGH_POWER = 0.75;
        public static final double LOW_POWER = 0.25;
        public static final int STRAFEINCH=135;
        public static final int INCH=90;//1120 cpr for neverest 40
        public static final int DEGREE=18;
        public static final String LF="frontLeft";
        public static final String LB="backLeft";
        public static final String RF="frontRight";
        public static final String RB="backRight";
    }

    public class Elevator {
        public static final float POWER = 1.0f;
        public static final String ELEVATOR="glypht";
        public static final int sixInches=4*1680;
        public static final double maxEncoder=26000;//1680 cpr for neverest 60
    }
    public class RelicArm{
        public static final int maxEncoder=30400;
        public static final String LEFTPIVOT="l_pivot";
        public static final String RIGHTPIVOT="r_pivot";
        public static final String ARM="relic";
        public static final String HAND="hand";
    }
    public class Grabber{
        public static final String LT="topLeft";
        public static final String LB="bottomLeft";
        public static final String RT="topRight";
        public static final String RB="bottomRight";
        public static final double openPos=0.5;
        public static final double closePos=1;
    }
    public class Rudder {
        public static final double RUDDER_START=0.4;
        public static final double RUDDER_IN = 0.4;
        public static final double RUDDER_OUT = 1;
        public static final String RUDDER = "rudder_servo";
        public static final String COLOR = "color";
    }

    public class Color {
        public static final int RED = 1;
        public static final int BLUE = 0;
        public static final int UNDECIDED = -1;
    }
    public static double floatToDouble(float f) {
        Float d=new Float(f);
        return d.doubleValue();
    }

    public static float doubleToFloat(double d){
        Double f=new Double(d);
        return f.floatValue();
    }

    public class Setup {
        public static final String VUFORIAKEY = "AYwm7lb/////AAAAGeQI9HT4B0R2unLNBq/DsId49BJr71nKGdfP8X8fnmtD0Jna47KLigPBytLYBjzOIl6uCfYWbIXHc3FqoabxIITohKJ4VvPispe5kGGFFJyQEIifEL1Bc511jOl00pyY2Tr/YOGwjGk7lSXQ0QrScHVaiwIOM3mUUlsv9Ethn1OCZB2AVhT91gnrUKryxBwfLCGjqpmYdWOVDsJTloDiowWMez0U42S9sILVevNguQXZqTr1uURaUx5Voy+2N6FVK5p4dvraac9+LD6YskUCLqWsK2XVruCpCsRWZxfrqylNyni2ll5AW3Mekw/hSSzfjx70eyKXyaLRiOj4UhHKCjeqWjFCePt0Vb59tyqd9KhS";
    }
}
