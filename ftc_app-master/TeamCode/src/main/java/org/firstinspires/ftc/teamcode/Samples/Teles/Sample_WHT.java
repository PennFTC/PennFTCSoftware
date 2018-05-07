package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Keatf on 5/5/2018.
 */
@TeleOp
public class Sample_WHT extends OpMode {
    public DcMotor leftdrive, rightdrive;

    public void init() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");

        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void loop() {
        double leftPower; double rightPower;
        if (gamepad1.a) {
            leftPower = (-gamepad1.left_trigger);
            rightPower = (-gamepad1.right_trigger);
        } else {
            leftPower = (gamepad1.left_trigger);
            rightPower = (gamepad1.right_trigger);
        }
        leftdrive.setPower(leftPower);
        rightdrive.setPower(rightPower);
    }
}
