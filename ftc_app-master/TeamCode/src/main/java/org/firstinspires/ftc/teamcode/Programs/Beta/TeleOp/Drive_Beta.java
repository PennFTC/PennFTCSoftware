package org.firstinspires.ftc.teamcode.Programs.Beta.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Programs.Beta.HardwareBeta;

import static java.lang.Math.PI;

@TeleOp
//@Disabled
public class Drive_Beta extends OpMode {
    public ElapsedTime mRuntime = new ElapsedTime();
    HardwareBeta robot = new HardwareBeta();

    public void init() {
        robot.init(hardwareMap);
    }

    public void loop() {
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;
        robot.leftfront.setPower(v1);
        robot.rightfront.setPower(v2);
        robot.leftback.setPower(v3);
        robot.rightback.setPower(v4);
        robot.lift.setPower(gamepad1.left_trigger);
        robot.lift.setPower(-gamepad1.right_trigger);
        if (gamepad2.right_bumper) {
            robot.leftbottomclaw.setPosition(1);
            robot.rightbottomclaw.setPosition(0);
        } else {
            robot.leftbottomclaw.setPosition(0);
            robot.rightbottomclaw.setPosition(1);
        }
        if (gamepad2.left_bumper) {
            robot.lefttopclaw.setPosition(1);
            robot.righttopclaw.setPosition(0);
        } else {
            robot.lefttopclaw.setPosition(0);
            robot.righttopclaw.setPosition(1);
        }
    }
}