package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Sample_Clawbot extends OpMode {
    DcMotor leftdrive, rightdrive;
    Servo leftclaw, rightclaw;

    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        leftclaw = hardwareMap.servo.get("leftclaw");
        rightclaw = hardwareMap.servo.get("rightclaw");

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        leftclaw.setPosition(0);
        rightclaw.setPosition(1);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.right_stick_y);
        if (gamepad1.a) {
            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
        } else {
            leftclaw.setPosition(0);
            rightclaw.setPosition(1);
        }
    }
}