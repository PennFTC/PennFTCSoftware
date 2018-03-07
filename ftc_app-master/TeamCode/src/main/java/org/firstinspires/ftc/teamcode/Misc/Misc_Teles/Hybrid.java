package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "hybrid")
//@Disabled
public class Hybrid extends OpMode {
    static final int numMotors = 4;

    static final int
            backleft = 3,
            frontleft = 0,
            backright = 1,
            frontright = 2;


    DcMotor[] DcMotors = new DcMotor[numMotors];

    @Override
    public void init() {
        for (int i = 0; i <= 3; i++) {
            DcMotors[i] = hardwareMap.dcMotor.get(Integer.toString(i));
            DcMotors[i].setPower(0);
            DcMotors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            DcMotors[1].setDirection(DcMotorSimple.Direction.REVERSE);
            DcMotors[2].setDirection(DcMotorSimple.Direction.REVERSE);
        }

    }

    public void loop() {
        double speed = 1.0;
        telemetry.addData("leftY", gamepad1.left_stick_y);
        telemetry.addData("leftX", gamepad1.left_stick_x);
        telemetry.addData("rightY", gamepad1.right_stick_x);
        telemetry.addData("rightY", gamepad1.right_stick_y);

        DcMotors[backleft].setPower(speed * gamepad1.left_stick_y);
        DcMotors[frontleft].setPower(speed * gamepad1.left_stick_y);
        DcMotors[backright].setPower(speed * gamepad1.right_stick_y);
        DcMotors[frontright].setPower(speed * gamepad1.right_stick_y);

        while (gamepad1.left_trigger > 0) {
            DcMotors[backleft].setPower(speed * gamepad1.left_trigger);
            DcMotors[frontleft].setPower(speed * gamepad1.left_trigger);
            DcMotors[backright].setPower(-speed * gamepad1.left_trigger);
            DcMotors[frontright].setPower(-speed * gamepad1.left_trigger);
        }

        while (gamepad1.right_trigger > 0) {
            DcMotors[backleft].setPower(-speed * gamepad1.right_trigger);
            DcMotors[frontleft].setPower(-speed * gamepad1.right_trigger);
            DcMotors[backright].setPower(speed * gamepad1.right_trigger);
            DcMotors[frontright].setPower(speed * gamepad1.right_trigger);
        }




    }
}


