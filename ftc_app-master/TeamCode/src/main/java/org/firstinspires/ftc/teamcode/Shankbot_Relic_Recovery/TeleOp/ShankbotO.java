package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.TeleOp;
// This is the package that we were given that contains all the required components of a ftc program
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbot;

/* We need imports in our program to be able to use the different functions that allow our
program to be what it is and to allow the different hardware components to work as they should. */
@TeleOp(name = "shankbotO") /*
This is one of our two drive programs that we have. This is the one that is primarily used when we
go out to do outreach  it is a bit more complex as it used arcade drive instead of tank.*/
//@Disabled
public class ShankbotO extends OpMode {
    HardwareShankbot bot = new HardwareShankbot(); // We use this to make our program shorter but it
    // does cause us to have an additional program. This is what imports the entire hardware config
    // from that program that we named HardwareShankbot
    private ElapsedTime mRuntime = new ElapsedTime(); // We use time in some of our programming so
    // we need to create time as something that can be used
    @Override
    public void start() {
        mRuntime.reset();
    } // From the time that we already created we have to start the "clock" on the robot.

    public void init() {
        bot.init(hardwareMap);// After we imported the program that contains all of our robot config
        // we had to set it as the active config of the robot in the program so the phone understands
        // what every part is.
        boolean bLedOn = true;// This is a yes or no function that we use to turn on the led on the
        // color sensor.
        boolean bLedOff= false; // This is a yes or no function that we use to turn off the led on
        // the color sensor.
        bot.colorS.enableLed(bLedOff); // This sets the led on the color sensor to be off.
        telemetry.addData("Status", "Ready"); // We have telemetry call out a message
        // from the phone to tell the drivers that the robot is ready to run
    }
    public void loop() {
        // doubles //
        double left;
        double right;
        double drive;
        double turn;
        double max;
        // Gamepad 1 //
        // floats //
        float gLY = gamepad1.left_stick_y; // renaming the y axis on the left joystick to something
        // shorter.
        float gLX = gamepad1.right_stick_x; // renaming the x axis on the right joystick to
        // something shorter.
        float gRY = gamepad1.right_stick_y; // renaming the y axis on the right joystick to
        // something shorter.
        float gLT = gamepad2.left_trigger; // renaming the left trigger to something shorter.
        float gRT = gamepad1.right_trigger; // renaming the right trigger to something shorter.
        boolean gRB = gamepad1.right_bumper; // renaming the right bumper to something shorter.
        boolean gLB = gamepad1.left_bumper; // renaming the left bumper to something shorter.
        boolean glB = gamepad1.left_stick_button; // renaming the button on the left joystick to
        // something shorter.
        //Booleans//
        boolean gA = gamepad1.a; // renaming the a button to something shorter.
        boolean gB = gamepad1.b; // renaming the b button to something shorter.
        boolean gX = gamepad1.x; // renaming the x button to something shorter.
        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        // displayed on the phone to tell us how much time has passed since we started the program.
        telemetry.addData("Status", "Initialized"); // Telemetry to say the robot is
        // initialized.
        drive = -gLY; // setting part of the drive program to run off of the negetive value of the y
        // axis on the left joystick.
        turn = (gLX * .8); // setting the other part of the drive program to run off, of a % of the
        // left x axis.
        left = drive + turn; // setting the left side of the robot to drive based on the previous
        // two values.
        right = drive - turn; // setting the right side of the robot to drive based on the previous
        // two values.
        max = Math.max(Math.abs(left), Math.abs(right)); // this sets the maximum possible speed the
        // robot can go.
        if (max > 1.0) {
            left /= max;
            right /= max;
        } // this prevents the left or the right side from reaching the maximum power.
        bot.leftdrive.setPower(left); // telling the robot that the left value is what gives the
        // power for leftdrive.
        bot.rightdrive.setPower(right); // telling the robot that the right value is what gives the
        // power for rightdrive.
        if (gA) {
            bot.leftwheel.setPower(1);
            bot.rightwheel.setPower(1);
        } else { // if we press the a button the collector wheels start spinning.
            if (gX) {
                bot.leftwheel.setPower(-1);
                bot.rightwheel.setPower(-1);
            } else { // if we press the x button the collector wheels start spinning in the opposite
                // direction.
                if (gB) {
                    bot.leftwheel.setPower(0);
                    bot.rightwheel.setPower(0);
                } // if we press the b button the collector wheels stop spinning.
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



