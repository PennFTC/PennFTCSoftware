package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp 
//@Disabled
public class Sample_Tank extends OpMode {
    public DcMotor leftdrive, rightdrive;

    public void start() {
    }

    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftdrive.setPower(0);
        rightdrive.setPower(0);
    }
// Hi
    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.right_stick_y);
    }
}