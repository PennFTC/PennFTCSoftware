package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.PI;

@TeleOp
public class Sample_H_Omni_Braden extends OpMode {
    DcMotor leftfront, rightfront, leftback, rightback, middle;

    public void init() {
        leftfront = hardwareMap.dcMotor.get("leftfront");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightback = hardwareMap.dcMotor.get("rightback");
        middle = hardwareMap.dcMotor.get("middle");
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
        middle.setPower(0);
    }

    public void loop() {
        double left;
        double right;
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        middle.setPower(gamepad1.right_stick_x);
        leftfront.setPower(left);
        leftback.setPower(left);
        rightfront.setPower(right);
        rightback.setPower(right);
    }
}

