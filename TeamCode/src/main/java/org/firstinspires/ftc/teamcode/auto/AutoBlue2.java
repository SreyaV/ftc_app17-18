package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.sensors.Vuforia;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */
@Autonomous
public class AutoBlue2 extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Grabber grabber;
    Gyroscope imu;
    int left = 10;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("color"));
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        grabber.autoClose();
        drivetrain.enableAndResetEncoders();
        detectedVuMark=vuforia.getVuMark();
        telemetry.addData("vumark",detectedVuMark);
        telemetry.update();
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        telemetry.update();
        Thread.sleep(1000);
        int color=rudder.getColor();
        if(color==Constants.Color.RED){
            drivetrain.moveFB(4,0.3);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4,-0.3);
        }else if(color==Constants.Color.BLUE){
            drivetrain.moveFB(-4,-0.3);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4,0.3);
        }else{
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
        }
        if (rudder.rudderServoPos() > Constants.Rudder.RUDDER_IN+0.1) {
            drivetrain.moveLR(-2, -0.3);
            rudder.setState(Rudder.RudderState.IN);
            drivetrain.moveLR(2, 0.3);
        }

        telemetry.update();
        drivetrain.moveFB(-7,-1);
        if (detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)) detectedVuMark = vuforia.getVuMark();
        drivetrain.moveFB(-1* 19, -1);
        drivetrain.pivot(-90,-1);
        if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(left,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(left +14,1);
        }else{
            drivetrain.moveFB(left +7,1);
        }
        telemetry.update();
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(9,1);
        grabber.autoOpen();
        drivetrain.moveFB(-10, -1);
        drivetrain.moveFB(11,0.5);
        drivetrain.moveFB(-3, .5);
    }
}
