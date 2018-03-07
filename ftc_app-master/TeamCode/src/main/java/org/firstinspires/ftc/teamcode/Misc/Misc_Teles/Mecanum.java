package org.firstinspires.ftc.teamcode.Misc.Misc_Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.PI;

@TeleOp(name = "Mecanum")
//@Disabled
public class Mecanum extends OpMode {
    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightRear;
    DcMotor leftRear;

    @Override
    public void init(){

        leftFront = hardwareMap.dcMotor.get("1");
        rightFront = hardwareMap.dcMotor.get("0");
        rightRear = hardwareMap.dcMotor.get("3");
        leftRear = hardwareMap.dcMotor.get("2");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        telemetry.addData("leftY", gamepad1.left_stick_y);
        telemetry.addData("leftX", gamepad1.left_stick_x);
        telemetry.addData("rightY", gamepad1.right_stick_x);



    }
}


