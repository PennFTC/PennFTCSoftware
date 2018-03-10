package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbot;

@TeleOp(name = "shankbotO")
public class ShankbotO extends OpMode {
    HardwareShankbot bot = new HardwareShankbot();
    private ElapsedTime mRuntime = new ElapsedTime();
    @Override
    public void start() {mRuntime.reset();}
    public void init() {
        bot.init(hardwareMap);
        telemetry.addData("Status", "Ready");
    }
    public void loop() {
        double left;
        double right;
        double drive;
        double turn;
        double max;

        float gLY = gamepad1.left_stick_y;
        float gLX = gamepad1.left_stick_x;
        float gRY = gamepad1.right_stick_y;
        float gLT = gamepad1.left_trigger;
        float gRT = gamepad1.right_trigger;
        boolean gRB = gamepad1.right_bumper;
        boolean gLB = gamepad1.left_bumper;
        boolean glB = gamepad1.left_stick_button;
        boolean gA = gamepad1.a;
        boolean gB = gamepad1.b;
        boolean gX = gamepad1.x;
        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");
        drive = gLY;
        turn = (-gLX * .8);
        left = drive + turn;
        right = drive - turn;
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;   }
        bot.leftdrive.setPower(left);
        bot.rightdrive.setPower(right);
        if (gA) {
            bot.leftwheel.setPower(1);
            bot.rightwheel.setPower(1);
        } else {
            if (gX) {
                bot.leftwheel.setPower(-1);
                bot.rightwheel.setPower(-1);
            } else {
                if (gB) {
                    bot.leftwheel.setPower(0);
                    bot.rightwheel.setPower(0);
                }
            }
        }
        if (gRB) { // if we press the right bumper it moves the relic servo into position.
            bot.rotate.setPosition(0);
        } else {
            bot.rotate.setPosition(1); // if we don't it doesn't move.
        }
        if (gLB) { // if we press the left bumper it moves the other relic servo into position.
            bot.extend.setPosition(0);
        } else {
            bot.extend.setPosition(1); // if we don't it doesn't move.
        }
        bot.slide.setPower(gRT);
        bot.slide.setPower(-gLT);
        // setting the power of our linear slide to correspond to either the right or left trigger.
        bot.arm.setPower(gRY);
        if (glB) { // if we push the left joystick button the color arm lowers.
            bot.thwack.setPosition(0);
        } else {
            bot.thwack.setPosition(1);
        } // if we don't it doesn't move.

        if (mRuntime.time() >= 90) {
            telemetry.addData("Status", "End Game");} // calling out after 90 seconds
        // have passed in the tele op that the end game has started
        if (mRuntime.time() >= 120) {
            telemetry.addData("Status", "Good Game");} // calling out the end of the
        // game after 2 minutes
        telemetry.update();
    }
}



