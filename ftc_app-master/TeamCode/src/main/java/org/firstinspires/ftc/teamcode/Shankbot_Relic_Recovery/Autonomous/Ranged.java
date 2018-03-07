package org.firstinspires.ftc.teamcode;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
// Imports are required to import the needed information from the package to allow the functions and
// hardware devices to run as properly and as told so.
/////////////////////////////////////////////////////////////////////// IMPORTS ////////////////////
@Autonomous(name = "range") // This defines to the phone what this program shows up on the phone as.
// This is the program that we use at outreach events as it causes the robot to wave at people. This
// program also helped us learn the concepts that are needed to use range sensors in autonomous
//@Disabled
public class Ranged extends LinearOpMode { // This defines this file named Ranged is a LinearOpmode
    // or that it is a autonomous program.
    // MOTORS //
    DcMotor leftdrive; // Defining that we have a motor named leftdrive
    DcMotor rightdrive; // Defining that we have a motor named rightdrive
    DcMotor arm; // Defining that we have a motor named arm
    // SERVOS //
    Servo leftclaw;// Defining that we have a servo named leftclaw.
    Servo rightclaw;// defining that we have a servo named rightclaw
    Servo thwack;// defining that we have a servo named thwack.
    // SENSORS //
    DigitalChannel touch; // Defining that we have a touch sensor named touch
    DistanceSensor colorR; // Defining that we have a distance sensor named colorR
    ColorSensor colorS; // Defining that we have a color sensor named colorS
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    ModernRoboticsI2cRangeSensor rangeSensor; // Defining we have a range sensor named rangesensor
    ModernRoboticsI2cRangeSensor rangeSensor1;// Defining we have a range sensor named rangesensor1

    OpticalDistanceSensor odsSensor; // defining we have a ods sensor named odsSensor
    ModernRoboticsI2cGyro gyro; // defining we have a gyro sensor named gyro.

    // BOOLEANS //
    boolean bLedOn = true; // creating a yes or no statement that indicates whether the light on the
    // color sensor is on or not.
    // TIME //
    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here
    @Override
    public void runOpMode() {

        mRuntime.reset(); // resetting the mRuntime
        thwack = hardwareMap.servo.get("0");  // naming the thwack 0, for the 0 servo port
        // NAMING SENSORS //
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "r0");
        rangeSensor1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "r1");


////////////////////////////////////////////////////////////// CONFIG //////////////////////////////
        thwack.setPosition(0);
        waitForStart();
        while (opModeIsActive()){
        rangerr();
        }}
public void down() {
        thwack.setPosition(.60);
        sleep(500);
} // method that sets the position of the servo
public void up() {
        thwack.setPosition(.2);
        sleep(500);
} // method that sets the position of the servo
public void wave() {
        down();
        up();
} // method that combines the previous two methods
    public void rangerr() {
        while (rangeSensor.rawUltrasonic() < (12 * 2.54)){
            wave();
        }
        while (rangeSensor1.rawUltrasonic() < (12 *2.54)){
            wave();
    } // This method has the robot wave if there is anything that is within 12 inches of either of
        // the range sensors.
}}
