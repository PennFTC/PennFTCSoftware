package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "outreach/alt control")
//@Disabled
public class outreach extends OpMode {
    DcMotor leftdrive;
    DcMotor rightdrive;
    DcMotor arm;
    DcMotor relic;

    Servo leftclaw;
    Servo rightclaw;
    Servo thwack;

    DigitalChannel touch;
    ColorSensor color;

    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {

        boolean bLedOn = true; // turning the light on, on the color sensor
        boolean bledOff = false;

        leftdrive = hardwareMap.dcMotor.get("0");
        rightdrive = hardwareMap.dcMotor.get("1");
        arm = hardwareMap.dcMotor.get("2");
        relic = hardwareMap.dcMotor.get("3");

        leftclaw = hardwareMap.servo.get("0");
        rightclaw = hardwareMap.servo.get("1");

        touch = hardwareMap.get(DigitalChannel.class, "touch");
        color = hardwareMap.get(ColorSensor.class , "color");

        leftdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        relic.setDirection(DcMotorSimple.Direction.REVERSE);
        leftclaw.setPosition(.25);
        rightclaw.setPosition(.5);

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        arm.setPower(0);

        leftdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        relic.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        color.enableLed(bledOff);
        touch.setMode(DigitalChannel.Mode.INPUT);

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

        drive = -gamepad1.left_stick_y;
        turn = (gamepad1.left_stick_x * .8);

        left = drive + turn;
        right = drive - turn;

        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");
        leftdrive.setPower(left);
        rightdrive.setPower(right);
        arm.setPower(gamepad1.right_stick_y);
        while (gamepad1.right_trigger > 0) {
            relic.setPower(.5 * gamepad1.right_trigger);
        }
        while (gamepad1.left_trigger > 0) {
            relic.setPower(-.5 * gamepad1.left_trigger);
        }
        if (gamepad2.right_stick_button) thwack.setPosition(1);

        if (gamepad2.left_stick_button) thwack.setPosition(0);

        if (gamepad1.right_bumper) {
            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
        } else {
            if  (gamepad1.left_bumper) {
                leftclaw.setPosition(0);
                rightclaw.setPosition(1);
            }  else {
                leftclaw.setPosition(.25);
                rightclaw.setPosition(.5);}
        }
    }
}
