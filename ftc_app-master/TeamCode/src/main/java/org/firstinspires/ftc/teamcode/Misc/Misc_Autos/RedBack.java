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
///////////////////////////////////////////////////////////// rAuto //////////////////////////////
@Autonomous(name = "Red") // Defining that on the phone this program will
// show up under the autonomous section with the name rAuto and under the subsection Red Alliance.


public class RedBack extends LinearOpMode { // Defining that this file named RAuto is a autonomous

    DcMotor leftdrive;
    DcMotor rightdrive;
    Servo thwack;
    ColorSensor colorS;

    @Override
    public void runOpMode() throws InterruptedException {
        //
        leftdrive = hardwareMap.dcMotor.get("0");
        rightdrive = hardwareMap.dcMotor.get("1");
        thwack = hardwareMap.servo.get("0");
        colorS = hardwareMap.colorSensor.get("color");
        telemetry.update();
        thwack.setPosition(1);
        waitForStart();

        jewelColor();

    }
    private void stapD() {
        leftdrive.setPower(0);
        rightdrive.setPower(0);
    }

    public void lowerThwack() {
        thwack.setPosition(0);
        sleep(2000);
    } // method that lowers moves the color arm to its downwards position.

    public void raiseThwack() {
        thwack.setPosition(1);
        sleep(500);
    } // method that raises the color arm to its upwards position.

    public void driveoffstoneR() {
        rightdrive.setPower(.25);
        leftdrive.setPower(.25);
        sleep(500);
        stapD();
    } // if the color of the jewel is red it will drive backward off the stone.

    public void driveoffstoneB() {
        rightdrive.setPower(-.25);
        leftdrive.setPower(-.25);
        sleep(500);
        stapD();
    }
    public void jewelColor() throws InterruptedException {
        lowerThwack();
        if (colorS.blue() > colorS.red()) {
            telemetry.addLine()
                    .addData("Color", colorS.blue())
                    .addData("Color", colorS.red());
            driveoffstoneB();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
            // if the color of the jewel the color sensor sees is blue then it performs these
            // methods.
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
}