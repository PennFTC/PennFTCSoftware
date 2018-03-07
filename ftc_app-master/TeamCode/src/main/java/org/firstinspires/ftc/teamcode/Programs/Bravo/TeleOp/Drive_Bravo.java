package org.firstinspires.ftc.teamcode.Programs.Bravo.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Programs.Bravo.HardwareBravo;

import static java.lang.Math.PI;
@TeleOp
//@Disabled
public class Drive_Bravo extends OpMode {
    public ElapsedTime mRuntime = new ElapsedTime();
    HardwareBravo robot = new HardwareBravo();
    public void init() {robot.init(hardwareMap);}
    public void loop() {
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;
        robot.leftfront.setPower(v1);   robot.rightfront.setPower(v2);
        robot.leftback.setPower(v3);    robot.rightback.setPower(v4);
        robot.lift.setPower(gamepad2.left_stick_y);
        if (gamepad2.right_bumper) {
            robot.leftclaw.setPosition(1);   robot.rightclaw.setPosition(0);
        } else {
            robot.leftclaw.setPosition(0);   robot.rightclaw.setPosition(1);}}}