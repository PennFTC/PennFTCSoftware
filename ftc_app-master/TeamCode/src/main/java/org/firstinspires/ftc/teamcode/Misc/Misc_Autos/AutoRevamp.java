package org.firstinspires.ftc.teamcode;

/**
 * Created by admin2 on 1/8/2018.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.UnknownFormatConversionException;

@Autonomous (name = "AutoRevamp")
public class AutoRevamp extends LinearOpMode{

    DcMotor leftdrive;
    DcMotor rightdrive;

    DcMotor arm;
    Servo leftclaw;
    Servo rightclaw;
    DigitalChannel touch;
    ColorSensor colorS;
    Servo thwack;
    VuforiaLocalizer vuforia;
    VuforiaTrackable relicTemplate;

    @Override
    public void runOpMode() throws InterruptedException{
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); // adding the existense of the camera
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId); // adding in the existence of parameters
        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiTvIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZLwn";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; // above is the lisence key needed for vuforia to work, and i am adding the parameter to use the back camera
        parameters.useExtendedTracking = false; // setting extended tracking to false
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters); // making vuforia have to use these parameters
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = (VuforiaTrackable)relicTrackables.get(0);


        leftdrive = hardwareMap.dcMotor.get("0"); // Naming the leftdrive 0, for the 0 port
        rightdrive = hardwareMap.dcMotor.get("1"); // Naming the rightdrive 1, for the 1 port
        arm = hardwareMap.dcMotor.get("2"); // Naming the arm 2, for the 2 port

        leftclaw = hardwareMap.servo.get("0"); // naming the leftclaw 0, for the 0 servo port
        rightclaw = hardwareMap.servo.get("1"); // naming the rightclaw 1, for the 1 servo port
        thwack = hardwareMap.servo.get("2");  // naming the thwack 2, for the 2 servo port

        colorS = hardwareMap.colorSensor.get("color"); // naming the color sensor
        touch = hardwareMap.digitalChannel.get("touch");

        waitForStart();
        vuforiaTracking();
    }

    RelicRecoveryVuMark markIdentity;

    public void vuforiaTracking() throws UnknownFormatConversionException {
        while (opModeIsActive())  {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("Vumark", "% visible" , vuMark);
                telemetry.update();
            }
            else {
                telemetry.addData("Vumark", "% visible" , vuMark);
                telemetry.update();

            }
            markIdentity = RelicRecoveryVuMark.valueOf(String.valueOf(vuMark));
            vuMark.equals(RelicRecoveryVuMark.valueOf(String.valueOf(vuMark)));
            telemetry.update();
        }

    }
}
