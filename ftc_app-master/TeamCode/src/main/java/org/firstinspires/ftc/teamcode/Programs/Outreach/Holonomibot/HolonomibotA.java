package org.firstinspires.ftc.teamcode.Programs.Outreach.Holonomibot;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/////////////////////////////////////////////////////////////////////// IMPORTS ////////////////////////////////////////////////////////////////////////////////////////////////////

@Autonomous(name="HolonomibotA")
//@Disabled
public class HolonomibotA extends LinearOpMode {
    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightRear;
    DcMotor leftRear;

    ModernRoboticsI2cRangeSensor rangeSensor;
    // TIME //
    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here
    // DOUBLES //
    boolean bLedOn = true;
    @Override public void runOpMode() {

        waitForStart();
        follow();
        while (opModeIsActive()) {
            telemetry.addData("raw ultrasonic", rangeSensor.rawUltrasonic());
    }
    }
    public void follow() {
        while( rangeSensor.rawUltrasonic() < 10) {
            forward();
            telemetry.addData(" raw ultrasonic", rangeSensor.rawUltrasonic());
        }
        while (rangeSensor.rawUltrasonic() > 10) {
            telemetry.addData(" raw ultrasonic", rangeSensor.rawUltrasonic());
        left_right();
        }
        while (rangeSensor.rawUltrasonic() == 0) {
           stap();
        }

    }
    public void forward() {
        rightFront.setPower(1);
        leftFront.setPower(1);
        rightRear.setPower(1);
        leftRear.setPower(1);
    }
    public void left_strafe() {
        rightFront.setPower(-1);
        leftFront.setPower(1);
        rightRear.setPower(1);
        leftRear.setPower(-1);
    }
    public void right_strafe() {
        rightFront.setPower(1);
        leftFront.setPower(-1);
        rightRear.setPower(-1);
        leftRear.setPower(1);
    }
    public void stap() {
        rightFront.setPower(0);
        leftFront.setPower(0);
        rightRear.setPower(0);
        leftRear.setPower(0);
    }
    public void leftright() {
        left_strafe();
        right_strafe();
        left_strafe();
        sleep(1000);
        stap();
        sleep(1);
        right_strafe();
        sleep(1000);
        stap();
        sleep(1);
    }
    public void left_right() {
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
        leftright();
    }
}