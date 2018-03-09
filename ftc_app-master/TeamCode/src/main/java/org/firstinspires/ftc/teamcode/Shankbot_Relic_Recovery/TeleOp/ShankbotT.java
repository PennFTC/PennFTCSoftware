package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.TeleOp;
// This is the package that we were given that contains all the required components of a ftc program.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbot;

/* We need imports in our program to be able to use the different functions that allow our
program to be what it is and to allow the different hardware components to work as they should. */
@TeleOp(name = "shankbotT") /*
 This is one of our two drive programs that we have. This is the one that is primarily used by our
 drive teams, it is a normal tank drive program that is rather simple. */
//@Disabled
public class ShankbotT extends OpMode {
    HardwareShankbot bot = new HardwareShankbot(); // We use this to make our program shorter but it
    // does cause us to have an additional program. This is what imports the entire hardware config
    // from that program that we named HardwareShankbot
    private ElapsedTime mRuntime = new ElapsedTime(); // We use time in some of our programmming so
    // we need to create time as something that can be used
    @Override
    public void start() {
        mRuntime.reset();
    } // From the time that we already created we have to start the "clock" on the robot.
    public void init() {
        bot.init(hardwareMap); // After we imported the program that contains all of our robot config
        // we had to set it as the active config of the robot in the program so the phone understands
        // what every part is.
        boolean bLedOn = true; // This is a yes or no function that we use to turn on the led on the color sensor.
        boolean bLedOff= false; // This is a yes or no function that we use to turn off the led on the color sensor.
        //bot.colorS.enableLed(bLedOff); // This sets the led on the color sensor to be off.
        telemetry.addData("Status", "Ready"); // We have telemetry call out a message
        // from the phone to tell the drivers that the robot is ready to run
    }
    public void loop() {
        // doubles //
        // Gamepad 1 //
        // floats //
        float g1LY = gamepad1.left_stick_y; // renaming the left stick y on the first gamepad to something shorter
        float g1RY = gamepad1.right_stick_y; // renaming the right stick y on the first gamepad to something shorter
        // Gamepad 2
        // floats //
        float g2LY = gamepad2.left_stick_y;  // renaming the left stick y on the second gamepad to something shorter
        float g2RY = gamepad2.right_stick_y;  // renaming the right stick y on the second gamepad to something shorter
        float g2lt = gamepad2.left_trigger; // renaming the left trigger on the second gamepad to something shorter
        float g2rt = gamepad2.right_trigger; // renaming the right trigger on the second gamepad to something shorter
        // booleans //
        boolean g2a = gamepad2.a; // Creating a boolean a for gamepad2.a to simplify code in the future
        boolean g2x = gamepad2.x; // Creating a boolean x for gamepad2.x to simplify code
        boolean g2b = gamepad2.b; // Creating a boolean b for gamepad2.b to simplify code in the future
        boolean g2y = gamepad2.y;
        boolean g2rBumper = gamepad2.right_bumper; // Creating a boolean rBumper for gamepad2.right_bumper
        // to simplify code in the future
        boolean g2lBumper = gamepad2.left_bumper; // Creating a boolean lBumper for gamepad2.left_bumper
        // to simplify code in the future
        telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // Telemetry
        // displayed on the phone to tell us how much time has passed since we started the program.
        telemetry.addData("Status", "Initialized"); // Telemetry to say the robot is initialized.
        bot.leftdrive.setPower(g1LY); // setting the leftdrive of the robot to have power corresponding
        // to the y axis on the left joystick on the first gamepad.
        bot.rightdrive.setPower(g1RY); // setting the rigtdrive of the robot to have power corresponding
        // to the y axis on the right joysitck on the first gamepad.

        if (g2a) {
            bot.leftwheel.setPower(-.8);
            bot.rightwheel.setPower(-.8); // if we press the a button on the second gamepad than the
            // collector wheels start spinning
        } else {
            if (g2x) {
                bot.leftwheel.setPower(.8);
                bot.rightwheel.setPower(.8);
            } else { // if we press the x button on the second gamepad the collector wheels start spinning in the opposite direction.
            if (g2b) {
                bot.leftwheel.setPower(0);
                bot.rightwheel.setPower(0);
            } // if we press the b button on the second gamepad then the collectors wheels stop spinning
        }}
        bot.slide.setPower(g2LY); // let our linear slide to have power corresponding to the left joystick's y axis on the second gamepad
        bot.arm.setPower(g2RY); // let our glyph arm to have power corresponding to the right joystick's y axis on the second gamepad

        if (g2lBumper) {
            bot.extend.setPosition(0);
        } else { // if we press the left bumper on the second gamepad than the servo that turns the relic up will go up
            bot.extend.setPosition(.5);
        } // if we don't press the left bumper on the second gamepad than the servo that turn the relic up will not go up.

        if (g2rBumper) {
            bot.rotate.setPosition(1);
        } else {
            // we press the right bumper on the second gamepad than the servo that grabs the relic will close.
            bot.rotate.setPosition(0);
        } // we don't press the right bumper on the second gamepad than the servo that grabs the relic will not close.
        if (gamepad2.left_stick_button) {
            bot.thwack.setPosition(0);
        } else { // if we press down on the left stick on the second gamepad than the color arm lowers.
            bot.thwack.setPosition(.8);
        }  // if we don't press down on the left stick on the second gamepad than the color arm stays where it is.
        if (mRuntime.time() >= 15 ) {
            telemetry.clear();
            telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // Telemetry
            telemetry.addData("Status", "Pick up the relic");
        }
        if (mRuntime.time() >= 75) {
            telemetry.clear();
            telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // Telemetry

            telemetry.addData("Status" , "Prepare to place the relic.");
        }
        if (mRuntime.time() >= 90) {
            telemetry.clear();
            telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // Telemetry

            telemetry.addData("Status", "End Game");} // calling out after 90 seconds have passed in the tele op that the end game has started
        if (mRuntime.time() >= 120) {
            telemetry.clear();
            telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // Telemetry
            telemetry.addData("Status", "Good Game");} // calling out the end of the game after 2 minutes
        telemetry.update();
    }
}



