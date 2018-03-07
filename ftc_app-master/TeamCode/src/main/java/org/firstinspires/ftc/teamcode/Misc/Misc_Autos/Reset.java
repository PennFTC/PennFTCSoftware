package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name = "Encoder", group = "TEST")
//@Disabled
public class Reset extends LinearOpMode {
    //Declaring motors

    DcMotor leftdrive;
    DcMotor rightdrive;

    DcMotor arm;
    Servo leftclaw;
    Servo rightclaw;
    TouchSensor touch;
    ColorSensor colorS;
    Servo thwack;
    //Declaring sensors
    boolean bLedOn = true; // turning the light on, on the color sensor
    // Declaring Vuforia stuff
    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables relicimages;
    VuforiaTrackable vumark;
    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     driveSpeed             = 0.8;
    static final double     turnSpeed              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); // adding the existense of the camera
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId); // adding in the existence of parameters

        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiTvIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZLwn";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; // above is the lisence key needed for vuforia to work, and i am adding the parameter to use the back camera
        parameters.useExtendedTracking = false; // setting extended tracking to false
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters); // making vuforia have to use these parameters

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark"); // inputting the trackables in
        VuforiaTrackable relicTemplate = relicTrackables.get(0); // disabling trackables
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate); // inputting in the vumarks

        mRuntime.reset(); // resetting the mRuntime
        leftdrive = hardwareMap.dcMotor.get("0"); // Naming the leftdrive 0, for the 0 port
        rightdrive = hardwareMap.dcMotor.get("1"); // Naming the rightdrive 1, for the 1 port
        arm = hardwareMap.dcMotor.get("2"); // Naming the arm 2, for the 2 port

        leftclaw = hardwareMap.servo.get("0"); // naming the leftclaw 0, for the 0 servo port
        rightclaw = hardwareMap.servo.get("1"); // naming the rightclaw 1, for the 1 servo port
        thwack = hardwareMap.servo.get("2");  // naming the thwack 2, for the 2 servo port

        colorS = hardwareMap.colorSensor.get("color"); // naming the color sensor
        touch = hardwareMap.touchSensor.get("touch");


        leftdrive.setPower(0); // setting the leftdrive to 0 power
        rightdrive.setPower(0); // setting the rightdrive to 0 power
        arm.setPower(0); // setting the arm to 0 power

        leftdrive.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the leftdrive to Reverse
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction of the right drive to Forward
        arm.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction of the arm mechanism of the robot to forward

        colorS.enableLed(bLedOn); // turning on the led on the color sensor
        telemetry.update();
        getRuntime();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        leftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Send telemetry message to indicate successful Encoder reset

        telemetry.update();


        waitForStart(); // forcing the program not to start until the play button is pushed
        encoderDrive(driveSpeed, -4, -4, 2.0);


        while (opModeIsActive()) {
            float[] hsvValues = new float[3];
            final float values[] = hsvValues;
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());
            telemetry.addData("Status", "Initialized");


            telemetry.update();

         /*  if (vuMark != RelicRecoveryVuMark.CENTER) {
                telemetry.addData("VuMark", "center visible");

            } else {
                if (vuMark != RelicRecoveryVuMark.RIGHT) {
                    telemetry.addData("VuMark", "right visible");

                } else {
                    if (vuMark != RelicRecoveryVuMark.LEFT) {
                        telemetry.addData("VuMark", "left visible");

                    } else {
                        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                            telemetry.addData("VuMark", "Nothing is visible ");

                        }
                    }
                }
            } */
        }
    }
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftdrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightdrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            leftdrive.setTargetPosition(newLeftTarget);
            rightdrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            mRuntime.reset();
            leftdrive.setPower(Math.abs(speed));
            rightdrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (mRuntime.seconds() < timeoutS) &&
                    (leftdrive.isBusy() && rightdrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftdrive.getCurrentPosition(),
                        rightdrive.getCurrentPosition());
                telemetry.update();}
        }
    }

    private void stap() {
        leftdrive.setPower(0);
        rightdrive.setPower(0);
    }

}


