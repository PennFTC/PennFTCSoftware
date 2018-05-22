package org.firstinspires.ftc.teamcode.Programs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by PennFTC on 5/22/2018.
 */
@TeleOp(name = "HBot", group = "Outreach Robots")
public class theOutreachBotTHINGY extends OpMode {
    // Drive Train
    DcMotor leftDrive, rightDrive, centerDrive;

    // Manipulator
    DcMotor lift;
    Servo leftClaw, rightClaw;

    public void init() {
        // Drive Train
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        centerDrive = hardwareMap.dcMotor.get("centerDrive");
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // Manipulator
        lift = hardwareMap.dcMotor.get("lift");
        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");
    }

    public void loop() {
        // Drive Train
        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);
        centerDrive.setPower(gamepad1.left_stick_x);
        // centerDrive.setPower(gamepad1.right_stick_x);

        //Manipulator
        lift.setPower(gamepad2.right_stick_y);
        if (gamepad2.b) {
           leftClaw.setPosition(-1);
           rightClaw.setPosition(1);
    }
}
