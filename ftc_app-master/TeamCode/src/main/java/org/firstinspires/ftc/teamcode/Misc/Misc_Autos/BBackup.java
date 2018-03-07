package org.firstinspires.ftc.teamcode;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
// Imports are required to import the needed information from the package to allow the functions and
// hardware devices to run as properly and as told so.
///////////////////////////////////////////////////////////// Rewrite //////////////////////////////
@Autonomous(name = "BI am ripped to shreds") // Defining that on the phone this program will
// show up under the autonomous section with the name bAuto and under the subsection Blue Alliance.
//@Disabled
public class BBackup extends LinearOpMode {// Defining that this file named BAuto is a autonomous
    // program or linearopmode.
    HardwareShankbot bot = new HardwareShankbot(); // This imports from the HardwareShankbot program
    // all the needed hardware devices that are on our robot.
    boolean bLedOn = true; // yes or no statement to indicate the light on the color sensor.
    boolean bLedOff = false; // yes or no statement to indicat ethe light on the color sensor.
    @Override
    public void runOpMode() {
        bot.init(hardwareMap); // importing the hardware settings that are defined in HardwareShank
        // bot.

////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////
        waitForStart();
        standard();

        telemetry.addLine()
                .addData("Status", "Run Time: " + bot.mRuntime.toString())
                .addData("Status", "Initialized");
        telemetry.update(); } // at all times during the program the drivers get to know how much
    // time has passed on the phone.
    /////////////////////////////////////////////////////////////////////////// METHODS ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void stapD() {
        bot.leftdrive.setPower(0);
        bot.rightdrive.setPower(0);
    } // simple method that is used quite often it stops the drive motors.
    public void getBallColor() {
        bot.colorS.enableLed(bLedOn);
        lowerThwack();
        telemetry.addLine()
                .addData("Color", bot.colorS.blue())
                .addData("Color", bot.colorS.red())
                .addData("in", "%.2f in",
                        bot.rangeSensor.getDistance(DistanceUnit.INCH));
        sleep(2000);}
    public void knockOffBall() {
        if (bot.colorS.blue() > bot.colorS.red()) {
            telemetry.addLine()
                    .addData("Color", bot.colorS.blue())
                    .addData("Color", bot.colorS.red())
                    .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            driveoffstoneB();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
        } else {
            telemetry.addLine()
                    .addData("Color", bot.colorS.blue())
                    .addData("Color", bot.colorS.red())
                    .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            driveoffstoneR();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
        }
    }
    public void lowerThwack() {
        bot.thwack.setPosition(1);
        sleep(2000);
    }
    public void raiseThwack() {
        bot.thwack.setPosition(0);
        sleep(500);
    }

    public void driveoffstoneR() {
        bot.rightdrive.setPower(.1);
        bot.leftdrive.setPower(.1);
        sleep(500);
        stapD();
    }
    public void driveoffstoneB() {
        bot.rightdrive.setPower(-.1);
        bot.leftdrive.setPower(-.1);
        sleep(500);
        stapD();
    }
    public void standard() {
        getBallColor();
        knockOffBall();
    }
}