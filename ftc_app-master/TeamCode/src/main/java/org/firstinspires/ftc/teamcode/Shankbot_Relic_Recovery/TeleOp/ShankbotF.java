package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "shankbotF")
//@Disabled
public class ShankbotF extends OpMode {
    HardwareShankbot bot = new HardwareShankbot();
    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {
        bot.init(hardwareMap);
        boolean bLedOn = true;
        boolean bLedOff= false;

        bot.colorS.enableLed(bLedOff);
        telemetry.addData("Status", "Ready");
    }

    public void loop() {
        // doubles //
        // Gamepad 1 //
        // floats //
        float g1LY = gamepad1.left_stick_y;
        float g1RY = gamepad1.right_stick_y;
        // booleans //
        boolean g1a = gamepad1.a;
        boolean g1b = gamepad1.b;
        // Gamepad 2

        // floats //
        float g2LY = gamepad2.left_stick_y;
        float g2RY = gamepad2.right_stick_y;

        float g2lt = gamepad2.left_trigger;
        float g2rt = gamepad2.right_trigger;
        // booleans //
        boolean g2a = gamepad2.a; // Creating a boolean a for gamepad2.a to simplify code in the future
        boolean g2b = gamepad2.b; // Creating a boolean b for gamepad2.b to simplify code in the future

        boolean g2rBumper = gamepad2.right_bumper; // Creating a boolean rBumper for gamepad2.right_bumper to simplify code in the future
        boolean g2lBumper = gamepad2.left_bumper; // Creating a boolean lBumper for gamepad2.left_bumper to simplify code in the future

        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");


         if (g1a) {
             bot.leftdrive.setDirection(DcMotorSimple.Direction.REVERSE);
             bot.rightdrive.setDirection(DcMotorSimple.Direction.FORWARD);
         if (g1b) {
             bot.leftdrive.setDirection(DcMotorSimple.Direction.FORWARD);
             bot.rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
             bot.leftdrive.setPower(g1RY);
             bot.rightdrive.setPower(g1LY);
         }
        bot.slide.setPower(.5 * g2lt);
        bot.slide.setPower(.5 * g2rt);

        if (g2a) {
            bot.leftwheel.setPower(-.8);
            bot.rightwheel.setPower(-.8);
        } else {
            if (g2b) {
                bot.leftwheel.setPower(0);
                bot.rightwheel.setPower(0);
            }
        }
        bot.arm.setPower(g2LY);
        bot.arm.setPower(g2RY);

        if (g2lBumper) {
            bot.extend.setPosition(0);
        } else {
            bot.extend.setPosition(1);
        }

        if (g2rBumper) {
            bot.rotate.setPosition(0);
        } else {
            bot.rotate.setPosition(1);
        }
        if (gamepad2.left_stick_button) {
            bot.thwack.setPosition(0);
        } else {
            bot.thwack.setPosition(.8);
        }

        if (mRuntime.time() >= 90) {
            telemetry.addData("Status", "End Game");} // calling out after 90 seconds have passed in the tele op that the end game has started
        if (mRuntime.time() >= 120) {
            telemetry.addData("Status", "Good Game");} // calling out the end of the game after 2 minutes
        telemetry.update();
    }
}}



