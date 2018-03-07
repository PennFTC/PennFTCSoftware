package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Dad2018")
@Disabled
public class Useless extends OpMode {
    static final int numMotors = 4;

    static final int
            backleft = 3,
            frontleft = 0,
            backright = 1,
            frontright = 2,
            arm = 4,
            arm1 =5;

    DcMotor[] DcMotors = new DcMotor[numMotors];

    Servo Claw;


    @Override
    public void init() {
        for (int i = 0; i <= 5; i++) {
            DcMotors[i] = hardwareMap.dcMotor.get(Integer.toString(i));
            DcMotors[i].setPower(0);
            if (i >= 2 && i <= 3) {
                DcMotors[i].setDirection(DcMotorSimple.Direction.REVERSE);
            } else {
                DcMotors[i].setDirection(DcMotorSimple.Direction.FORWARD);
            }
        }
        Claw = hardwareMap.servo.get("0");

    }

    public void loop() {
        telemetry.addData("leftY", gamepad1.left_stick_y);
        telemetry.addData("leftX", gamepad1.left_stick_x);
        telemetry.addData("rightY", gamepad1.right_stick_x);

        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;

        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        DcMotors[frontleft].setPower(v1);
        DcMotors[frontright].setPower(v2);
        DcMotors[backleft].setPower(v3);
        DcMotors[backright].setPower(v4);

        DcMotors[arm].setPower(gamepad2.left_stick_y);
        DcMotors[arm1].setPower(gamepad2.right_stick_y);

        if (gamepad2.a) {
            Claw.setPosition(1);
        } else {
            if (gamepad2.b) {
                Claw.setPosition(.5);
            } else {
                Claw.setPosition(0);
            }
        }
    }
}

