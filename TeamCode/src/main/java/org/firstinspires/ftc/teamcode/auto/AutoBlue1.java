package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.sensors.Vuforia;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */
@Autonomous
public class AutoBlue1 extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    @Override
    public void runOpMode() throws InterruptedException {
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        detectedVuMark=vuforia.getVuMark();
        telemetry.addData("vumark",detectedVuMark);telemetry.update();
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDER), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        drivetrain.enableAndResetEncoders();
        telemetry.addData("status", "started"); telemetry.update();
        drivetrain.moveLR(3*Constants.Drivetrain.STRAFEINCH, 1); // move 3 inches right
        //drivetrain.pivot(-15*Constants.Drivetrain.DEGREE,-1);
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        // knock off red
        int color=rudder.getColor();
        if(color==Constants.Color.BLUE){
            drivetrain.pivot(1000,1);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            Thread.sleep(1000);
            drivetrain.pivot(-1000,-1);
        }else if(color==Constants.Color.RED){
            drivetrain.pivot(-1000,-1);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            Thread.sleep(1000);
            drivetrain.pivot(1000,1);
        }else{
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            Thread.sleep(1000);
        }
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(-25*Constants.Drivetrain.INCH,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(-50*Constants.Drivetrain.INCH,1);
        }else{
            drivetrain.moveFB(-37*Constants.Drivetrain.INCH,1);
        }
        drivetrain.pivot(90*Constants.Drivetrain.DEGREE,1);
        drivetrain.moveFB(6*Constants.Drivetrain.INCH,1);
    }
}
