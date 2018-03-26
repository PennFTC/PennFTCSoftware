package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

@TeleOp
public class Sample_Kiwi extends OpMode {
    DcMotor left, right, back;

    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        back = hardwareMap.dcMotor.get("back");
    }
    public void loop() {
       double X;
       double Y;
       double left;
       double right;
       double back;
       X = gamepad1.left_stick_x;
       Y = gamepad1.left_stick_y;
       left = (-1/2 * X - sqrt(3)/2 * Y);
       right = (-1/2 * X + sqrt(3)/2 * Y);
       back = X;
    }
}

