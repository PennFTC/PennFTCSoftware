package org.firstinspires.ftc.teamcode.Programs.Bermuda.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Programs.Bermuda.HardwareBermuda;

import static java.lang.Math.PI;
@TeleOp
//@Disabled
public class Drive_Bermuda extends OpMode{
    HardwareBermuda robot = new HardwareBermuda();
    public void init() {robot.init(hardwareMap);}
    public void loop() { // HI
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;
        robot.leftfront.setPower(v1);   robot.rightfront.setPower(v2);
        robot.leftback.setPower(v3);    robot.rightback.setPower(v4);   }}