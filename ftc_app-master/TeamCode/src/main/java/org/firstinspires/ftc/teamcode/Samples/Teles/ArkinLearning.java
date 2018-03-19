package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/**
 * Created by admin2 on 3/15/2018.
 */
@TeleOp
@Disabled
public class ArkinLearning extends OpMode {
    public DcMotor leftdrive, rightdrive;

    public void start() {}


    public void init () {
        leftdrive = hardwareMap.dcMotor.get ("leftdrive");
        rightdrive = hardwareMap.dcMotor.get ("leftdrive");
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightdrive.setPower(0);
        leftdrive.setPower(0);
    }


    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.right_stick_y);
    }
}