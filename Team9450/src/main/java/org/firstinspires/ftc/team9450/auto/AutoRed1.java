package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.sensors.Vuforia;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/24/2018.
 */
@Autonomous
@Disabled
public class AutoRed1 extends LinearOpMode {
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Gyroscope imu;
    Ramp ramp;
    Intake intake;
    int center=-22;
    int glyphPit=10;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        //initialize subsystems
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP),hardwareMap.dcMotor.get(Constants.Ramp.LIFT),hardwareMap.digitalChannel.get("touch"));
        vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));


        //detect vumark
        detectedVuMark = vuforia.getVuMark();
        telemetry.addData("vumark", detectedVuMark);
        telemetry.update();

        // knock jewel off

        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setRudderState(Rudder.RudderState.OUT);
        rudder.loop();
        Thread.sleep(1000);
        int color = rudder.getColor();
        if (color == Constants.Color.BLUE) {
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
            rudder.loop();
            Thread.sleep(500);
        } else if (color == Constants.Color.RED) {
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
            rudder.loop();
            Thread.sleep(500);
        }
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(500);

        drivetrain.moveFB(-12,-0.5);
        straighten();
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(-1*center-5,-0.5);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(-1*center+5,-0.5);
        }else{
            drivetrain.moveFB(-1*center+5,-0.5);
            //drivetrain.moveFB(-1*center,-0.5);
        }
        pivot(Math.PI/3,true);
        drivetrain.moveFB(1.5*Math.sqrt(2)+4,0.6);
        intake.setPower(.5);
        //Thread.sleep(1000);
        drivetrain.moveFB(-5, -0.6);
        //drivetrain.moveFB(5, 0.6);
        //drivetrain.moveFB(2, 1);
        //drivetrain.moveFB(-2, -1);
        //intake.setState(Intake.IntakeState.OFF);
        intake.setPower(0);
        if ( detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){//idk what to do with this line -Grace
            drivetrain.moveFB(-2, -1);
        }
        //next glyph
        //straighten();
        pivot(3*Math.PI/4, false);
        intake.setPower(-1);
        drivetrain.moveFB(18, 0.7);
        //straighten();

        //pivot(Math.PI/10, true);
        //pivot(Math.PI/10, false);
        drivetrain.moveFB(5, 0.7);
        //straighten();

        drivetrain.moveFB(-10, -0.7);
        drivetrain.moveFB(16,0.7);
        drivetrain.moveFB(-26,-0.7);

        if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            pivot(Math.PI/8,false);
            drivetrain.moveFB(-2,-0.5);
        }


        intake.setPower(0);
        //drivetrain.moveFB(-1.5,-0.5);
        drivetrain.moveFB(-1,-.5);
        //drivetrain.moveFB(-.2,-0.5);
        //straighten();
        drivetrain.moveFB(3, .5);
        drivetrain.moveFB(-3, -.5);
        drivetrain.moveFB(1,0.75);
        ramp.setRampState(Ramp.RampState.IN);
        ramp.loop();


        intake.setPower(0);
    }
    public void goToPitLeft(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        pivot(Math.PI/4,true);
        pivot(Math.PI/2,false);
        pivot(Math.PI/4,true);
        drivetrain.moveFB(-1*glyphPit,-1);
        intake.setState(Intake.IntakeState.OFF);
        pivot(Math.PI/2,true);
        drivetrain.moveFB(-1*distance,-1);
    }
    public void goToPitRight(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        pivot(Math.PI/4,true);
        pivot(Math.PI/2,false);
        pivot(Math.PI/4,true);
        drivetrain.moveFB(-1*glyphPit,-1);
        intake.setState(Intake.IntakeState.OFF);
        pivot(Math.PI/2,true);
        drivetrain.moveFB(distance,1);
    }
    public void dropGlyphs()throws InterruptedException{
        pivot(Math.PI/4,false);
        drivetrain.moveFB(-4,-1);
        ramp.setRampState(Ramp.RampState.OUT);ramp.loop();Thread.sleep(500);
        ramp.setRampState(Ramp.RampState.IN);ramp.loop();Thread.sleep(500);
        drivetrain.moveFB(4,1);
        pivot(Math.PI/4,false);
    }
    public void pivot(double angle, boolean cc) {
        double Q = Math.PI/25;
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        if (cc) {
            drivetrain.setPower(new double[]{-0.3, -0.3, 0.3, 0.3});
        } else {
            drivetrain.setPower(new double[]{0.3, 0.3, -0.3, -0.3});
        }
        while (opModeIsActive() && Math.abs(imu.getAngle()) < angle - Q) {}
        drivetrain.setPower(0);
    }
    public void straighten() {
        if(imu.getAngle() > 0){
            drivetrain.setPower(new double[]{0.3,0.3,-0.3,-0.3});
            while(opModeIsActive() && imu.getAngle() > 0.05){}
        }else if(imu.getAngle() < 0){
            drivetrain.setPower(new double[]{-0.3,-0.3,0.3,0.3});
            while(opModeIsActive() && imu.getAngle() < -0.05){}
        }
        drivetrain.setPower(new double[]{0,0,0,0});
    }
}