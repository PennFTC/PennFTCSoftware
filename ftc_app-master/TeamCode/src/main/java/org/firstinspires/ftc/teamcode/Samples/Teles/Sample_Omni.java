package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Sample_Omni extends OpMode {
    DcMotor leftdrive, rightdrive, middledrive;

    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        middledrive = hardwareMap.dcMotor.get("middle");

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        middledrive.setPower(0);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.left_stick_y);
        middledrive.setPower(gamepad1.left_stick_x);
    }
}

