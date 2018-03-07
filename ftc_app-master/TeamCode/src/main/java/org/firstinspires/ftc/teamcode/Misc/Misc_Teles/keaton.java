package org.firstinspires.ftc.teamcode.Misc.Misc_Teles;

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
public class keaton extends OpMode {
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

    DigitalChannel touch;
    ColorSensor color;

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

            touch = hardwareMap.get(DigitalChannel.class, "touch");
            color = hardwareMap.get(ColorSensor.class , "color");

            leftclaw.setPosition(.25);
            rightclaw.setPosition(.5);

            DcMotors[backleft].setPower(0);

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
        turn  = (gamepad1.left_stick_x * .8);

        left  = drive + turn;
        right = drive - turn;

        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");


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
