package org.firstinspires.ftc.teamcode.Misc.Learning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by admin2 on 3/19/2018.
 */

    @TeleOp
    public class RightStickArcadeCode extends OpMode {
        DcMotor leftdrive, rightdrive;
        Servo leftclaw, rightclaw;

        public void init() {
            leftdrive = hardwareMap.dcMotor.get("leftdrive");
            rightdrive = hardwareMap.dcMotor.get("rightdrive");
            leftclaw = hardwareMap.servo.get("leftclaw");
            rightclaw = hardwareMap.servo.get("rightclaw");
            leftdrive.setPower(0);
            rightdrive.setPower(0);
            leftclaw.setPosition(0);
            rightclaw.setPosition(1);
            rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        //test
        public void loop() {
            double drive;
            double turn;
            double left;
            double right;
            double max;
            drive = -gamepad1.right_stick_y;
            turn = (gamepad1.right_stick_x * .8);
            left = drive + turn;
            right = drive - turn;
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }
            leftdrive.setPower(left);
            rightdrive.setPower(right);

            if (gamepad1.right_bumper) {
                rightclaw.setPosition(.78);
                leftclaw.setPosition(.22);
            }else {
                rightclaw.setPosition(1);
                leftclaw.setPosition(0);
            }
        }
}
