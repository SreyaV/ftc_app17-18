package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Grace on 1/27/2018.
 */
@Autonomous
public class IntakeDeployTest extends LinearOpMode{
    CRServo crServo;
    public void runOpMode() throws InterruptedException {
        crServo=hardwareMap.crservo.get("intakedeploy");
        crServo.setDirection(DcMotorSimple.Direction.FORWARD);
        crServo.setPower(1);
        Thread.sleep(500);
        crServo.setPower(0);
    }
}