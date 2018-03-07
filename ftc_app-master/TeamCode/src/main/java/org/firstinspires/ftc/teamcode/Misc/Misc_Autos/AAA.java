package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import static java.lang.Math.PI;

@TeleOp(name = "AAAA")
//@Disabled
public class AAA extends OpMode {
   DcMotor frontleft;
   DcMotor frontright;
   DcMotor backleft;
   DcMotor backright;
   DcMotor lift;

   Servo rightarm;
   Servo leftarm;
    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {
        frontleft = hardwareMap.dcMotor.get("0");
        frontright = hardwareMap.dcMotor.get("1");
        backleft = hardwareMap.dcMotor.get("2");
        backright = hardwareMap.dcMotor.get("3");
        lift = hardwareMap.dcMotor.get("4");

        rightarm = hardwareMap.servo.get("a0");
        leftarm = hardwareMap.servo.get("a1");

        telemetry.addData("Status", "Ready");
    }

    public void loop() {
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        frontleft.setPower(v1);
        frontright.setPower(v2);
        backleft.setPower(v3);
        backright.setPower(v4);

        lift.setPower(gamepad1.left_trigger);
        lift.setPower(-gamepad1.right_trigger);

        if (gamepad1.right_bumper) {
            rightarm.setPosition(.2);
            leftarm.setPosition(.9);
        } else {
            rightarm.setPosition(.9);
            leftarm.setPosition(.2);
        }

        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");

    }}



