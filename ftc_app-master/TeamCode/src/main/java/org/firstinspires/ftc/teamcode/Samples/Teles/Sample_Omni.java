package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
@Disabled
public class Sample_Omni extends OpMode{
    DcMotor leftdrive, rightdrive, middle;
    public void start(){}
    public void init(){
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        middle = hardwareMap.dcMotor.get("middle");

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        middle.setPower(0);

        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.left_stick_y);
        middle.setPower(gamepad1.left_stick_x);
    }
}

