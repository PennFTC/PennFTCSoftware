package org.firstinspires.ftc.teamcode.Programs.Outreach.Holonomibot;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Programs.Outreach.Holonomibot.HardwareHolonomibot;

import static java.lang.Math.PI;
// These are all the imports that are used for this program, or these are all the specific pieces of
// data we need for this program to run properly.
@TeleOp(name = "Holinomibot") // This is the drive program for our outreach bot the holonomibot
//@Disabled
public class Holonomibot extends OpMode {
    public ElapsedTime mRuntime = new ElapsedTime(); // setting time as a variable that can be used.
    HardwareHolonomibot bot = new HardwareHolonomibot(); // importing the hardware from the
    // hardwareHolonomibot file.
    @Override
    public void start() {
        mRuntime.reset();} // when we push the start button it resets the time if it was already
    // running.
    public void init() {
        bot.init(hardwareMap);
    } // from the hardwareholonomibot file we import all the config settings.
    @Override
    public void loop() {
        telemetry.addData("leftY", gamepad1.left_stick_y); // get the position of the left
        // joystick y axis
        telemetry.addData("leftX", gamepad1.left_stick_x); // get the position of the left
        // joystick x axis
        telemetry.addData("rightY", gamepad1.right_stick_x); // get the position of the
        // right joystick y axis
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y); // getting a value that
        // is used for the holonomic drive
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - PI / 4; //
        // getting a estimated angle of the robot to be used for holonomic drive.
        double rightX = -gamepad1.right_stick_x; // the opposite value of the right joysticks x axis
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;
        // The calculated different drive setting that allow the robot to run using a holonomic
        // drive
        bot.leftfront.setPower(v1);
        bot.rightfront.setPower(v2);
        bot.leftrear.setPower(v3);
        bot.rightrear.setPower(v4);
        // setting the previous calculated values to there respective motors to drive properly,
    }}