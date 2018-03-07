package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "outreach/alt control")
//@Disabled
public class bow extends OpMode {
    static final int numMotors = 6;

    static final int
            backleft = 3,
            frontleft = 0,
            backright = 1,
            frontright = 2,
            arm = 4,
            armextender = 5;

    DcMotor[] DcMotors = new DcMotor[numMotors];

    Servo leftclaw;
    Servo rightclaw;


    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program

    @Override
    public void start() {
        mRuntime.reset();
    }

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
        boolean bLedOn = true; // turning the light on, on the color sensor
        boolean bledOff = false;


        leftclaw = hardwareMap.servo.get("0");
        rightclaw = hardwareMap.servo.get("1");


        leftclaw.setPosition(.25);
        rightclaw.setPosition(.5);


        telemetry.addData("Status", "Ready");
    }

    public void loop() {
        //doubles
        double left;
        double right;
        double drive;
        double turn;
        double max;

        //booleans
        boolean a = gamepad2.a; // Creating a boolean a for gamepad1.a to simplify code in the future
        boolean b = gamepad2.b; // Creating a boolean b for gamepad1.b to simplify code in the future
        boolean rBumper = gamepad1.right_bumper; // Creating a boolean rBumper for gamepad1.right_bumper to simplify code in the future
        boolean lBumper = gamepad1.left_bumper; // Creating a boolean lBumper for gamepad1.left_bumper to simplify code in the future
        boolean dUp = gamepad1.dpad_up; // Creating a boolean dUp for gamepad1.dpad_up to simplify code in the future
        boolean dDown = gamepad1.dpad_down; // Creating a boolean dDown for gamepad1.dpad_down to simplify code in the future

        //floats
        float g1LY = gamepad1.left_stick_y;
        float g1RY = gamepad1.right_stick_y;
        float g1LX = gamepad1.left_stick_x;
        float g1RX = gamepad1.right_stick_x;
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

        DcMotors[arm].setPower(.5 * gamepad2.left_stick_y);
        DcMotors[armextender].setPower(gamepad2.right_stick_y);

        if (gamepad2.right_bumper) {
            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
        } else {
            if  (gamepad2.left_bumper) {
                leftclaw.setPosition(0);
                rightclaw.setPosition(1);
            }  else {
                leftclaw.setPosition(.25);
                rightclaw.setPosition(.5);}
        }
    }
}