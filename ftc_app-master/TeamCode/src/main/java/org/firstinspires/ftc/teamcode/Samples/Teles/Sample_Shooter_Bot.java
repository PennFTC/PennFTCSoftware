package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
//@Disabled
public class Sample_Shooter_Bot extends OpMode {
    DcMotor leftdrive, rightdrive, leftwheel, rightwheel;
    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        leftwheel = hardwareMap.dcMotor.get("leftwheel");
        rightwheel = hardwareMap.dcMotor.get("rightwheel");

        leftdrive.setPower(0);  rightdrive.setPower(0);
        leftwheel.setPower(0);  rightwheel.setPower(0);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightwheel.setDirection(DcMotorSimple.Direction.REVERSE);   }
    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y); rightdrive.setPower(gamepad1.right_stick_y);
        if (gamepad1.left_bumper) {
            leftwheel.setPower(1);  rightwheel.setPower(1);
        } else { if (gamepad1.right_bumper) {
            leftwheel.setPower(-1); rightwheel.setPower(-1);
        } else {
            leftwheel.setPower(0);  rightwheel.setPower(0);                                     }}}}