package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.PI;

@TeleOp
public class Sample_H_Omni_Smart extends OpMode {
    private DcMotor leftfront, rightfront, leftback, rightback, middle;

    public void init() {
        leftfront = hardwareMap.dcMotor.get("leftfront");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightback = hardwareMap.dcMotor.get("rightback");
        middle = hardwareMap.dcMotor.get("middle");
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        leftfront.setPower(0);  rightfront.setPower(0);
        leftback.setPower(0);   rightback.setPower(0);
        middle.setPower(0);
    }

    public void loop() {
        double drive;   double turn;    double right;   double left;    double max;
        boolean g1LB = gamepad1.left_bumper;
        boolean g1RB = gamepad1.right_bumper;
        float G1LY = gamepad1.left_stick_y;
        float G1LX = gamepad1.left_stick_x;
        drive = -G1LY;  turn = (G1LX);
        left = drive + turn;    right = drive - turn;
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {    left /= max;    right /= max;}
        if (gamepad1.left_bumper) { middle.setPower(-1);
        } else if (gamepad1.right_bumper) { middle.setPower(1);
        } else {    middle.setPower(0);}
        leftfront.setPower(left);   leftback.setPower(left);
        rightfront.setPower(right); rightback.setPower(right);
    }
}

