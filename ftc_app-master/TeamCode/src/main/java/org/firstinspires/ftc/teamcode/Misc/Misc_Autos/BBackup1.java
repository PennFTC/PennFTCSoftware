package org.firstinspires.ftc.teamcode;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

// Imports are required to import the needed information from the package to allow the functions and
// hardware devices to run as properly and as told so.
///////////////////////////////////////////////////////////// Rewrite //////////////////////////////
@Autonomous(name = "BI am ripped to shreds1") // Defining that on the phone this program will
// show up under the autonomous section with the name bAuto and under the subsection Blue Alliance.
//@Disabled
public class BBackup1 extends LinearOpMode {// Defining that this file named BAuto is a autonomous
    public DcMotor leftdrive   = null; // Creatting the leftdrive of the robot and indicating that
    // it is a DcMotor.
    public DcMotor rightdrive  = null; // Creating the rightdrive of the robot and indicating that
    public Servo thwack = null; // creating the color arm of the robot and indicating that it is a
    // Servo.
    public ColorSensor colorS = null; // creating the color sensor and indicating that it is a color
    // sensor.
    public ElapsedTime mRuntime  = new ElapsedTime(); // creating time as a variable
    boolean bLedOn = true; // yes or no statement to indicate the light on the color sensor.
    boolean bLedOff = false; // yes or no statement to indicat ethe light on the color sensor.
    @Override
    public void runOpMode() {
        //

////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////
        waitForStart();
        standard();

        telemetry.addLine()
                .addData("Status", "Run Time: " + mRuntime.toString())
                .addData("Status", "Initialized");
        telemetry.update(); } // at all times during the program the drivers get to know how much
    // time has passed on the phone.
    /////////////////////////////////////////////////////////////////////////// METHODS ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void stapD() {
        leftdrive.setPower(0);
        rightdrive.setPower(0);
    } // simple method that is used quite often it stops the drive motors.
    public void getBallColor() {
        colorS.enableLed(bLedOn);
        lowerThwack();
        telemetry.addLine()
                .addData("Color", colorS.blue())
                .addData("Color", colorS.red());
        sleep(2000);}
    public void knockOffBall() {
        if (colorS.blue() > colorS.red()) {
            telemetry.addLine()
                    .addData("Color", colorS.blue())
                    .addData("Color", colorS.red());
            driveoffstoneB();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
        } else {
            telemetry.addLine()
                    .addData("Color", colorS.blue())
                    .addData("Color", colorS.red());
            driveoffstoneR();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
        }
    }
    public void lowerThwack() {
        thwack.setPosition(1);
        sleep(2000);
    }
    public void raiseThwack() {
        thwack.setPosition(0);
        sleep(500);
    }

    public void driveoffstoneR() {
        rightdrive.setPower(.1);
        leftdrive.setPower(.1);
        sleep(500);
        stapD();
    }
    public void driveoffstoneB() {
        rightdrive.setPower(-.1);
        leftdrive.setPower(-.1);
        sleep(500);
        stapD();
    }
    public void standard() {
        getBallColor();
        knockOffBall();
    }
}