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
@Autonomous(name = "ErrorAuto") // This defines to the phone what this program shows up on the phone as.
// This is the program that we use at outreach events as it causes the robot to wave at people. This
// program also helped us learn the concepts that are needed to use range sensors in autonomous
//@Disabled
public class ErrorAuto extends LinearOpMode { // This defines this file named Ranged is a LinearOpmode
    DcMotor leftdrive;
    DcMotor rightdrive;

    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here
    @Override
    public void runOpMode() {
        leftdrive = hardwareMap.dcMotor.get("0");
        rightdrive = hardwareMap.dcMotor.get("1");
        mRuntime.reset(); // resetting the mRuntime
        waitForStart();
       driveforward();
       sleep(1000);
       tryturn();
       sleep(5000);
       drivebackward();
       sleep(1305);
         }
         public void driveforward() {
        leftdrive.setPower(.2);
        rightdrive.setPower(.15);
         }
         public void tryturn() {
        leftdrive.setPower(.09);
        rightdrive.setPower(-.09);
         }
         public void drivebackward() {
        leftdrive.setPower(-.25);
        rightdrive.setPower(-.665);
         }
}
