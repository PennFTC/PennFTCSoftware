package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Sample_Arcade extends OpMode {
    DcMotor leftdrive, rightdrive;

    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        leftdrive.setPower(0);
        rightdrive.setPower(0);

        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        double drive;
        double turn;
        double left;
        double right;
        double max;
        drive = -gamepad1.left_stick_y;
        turn = (gamepad1.left_stick_x * .8);
        left = drive + turn;
        right = drive - turn;
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }
        leftdrive.setPower(left);
        rightdrive.setPower(right);
    }
}
