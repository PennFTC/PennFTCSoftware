package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AKeaton")
//@Disabled
public class AKeaton extends LinearOpMode {
    //Declaring motors

    DcMotor backleft;
    DcMotor frontleft;
    DcMotor backright;
    DcMotor frontright;

    //DcMotor arm;
   // DcMotor armextender;

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


        mRuntime.reset(); // resetting the mRuntime
        backleft = hardwareMap.dcMotor.get("0");
        frontleft = hardwareMap.dcMotor.get("1");
        backright = hardwareMap.dcMotor.get("2");
        frontright = hardwareMap.dcMotor.get("3");

        //arm = hardwareMap.dcMotor.get("4");
       // armextender = hardwareMap.dcMotor.get("5");

        backleft.setPower(0);
        frontleft.setPower(0);
        backright.setPower(0);
        frontright.setPower(0);
      //  arm.setPower(0);




       // arm.setDirection(DcMotorSimple.Direction.REVERSE);
      //  armextender.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.update();
        getRuntime();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        telemetry.update();

        waitForStart(); // forcing the program not to start until the play button is pushed
        encoderDrive(driveSpeed, 4.0, 4.0, 4.0, 4.0, 2.0);
        stap();

        telemetry.update();

        while (opModeIsActive()) {
            float[] hsvValues = new float[3];
            final float values[] = hsvValues;
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());
            telemetry.addData("Status", "Initialized"); }}


    public void encoderDrive(double speed, double backleftInches, double frontleftInches, double backrightInches, double frontrightInches,  double timeoutS) {
        int newBackLeftTarget;
        int newFrontLeftTarget;
        int newBackRightTarget;
        int newFrontRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newBackLeftTarget = backleft.getCurrentPosition() + (int)(backleftInches * COUNTS_PER_INCH);
            newFrontLeftTarget = frontleft.getCurrentPosition() + (int)(frontleftInches * COUNTS_PER_INCH);
            newBackRightTarget = frontleft.getCurrentPosition() + (int)(backrightInches * COUNTS_PER_INCH);
            newFrontRightTarget = frontleft.getCurrentPosition() + (int)(frontrightInches * COUNTS_PER_INCH);

            backleft.setTargetPosition(newBackLeftTarget);
            frontleft.setTargetPosition(newFrontLeftTarget);
            backleft.setTargetPosition(newBackLeftTarget);
            backleft.setTargetPosition(newBackLeftTarget);

            // Turn On RUN_TO_POSITION
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            mRuntime.reset();
            backleft.setPower(Math.abs(speed));
            frontleft.setPower(Math.abs(speed));
            backright.setPower(Math.abs(speed));
            frontright.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (mRuntime.seconds() < timeoutS) &&
                    (backleft.isBusy() && frontleft.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newBackLeftTarget,  newFrontLeftTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        backleft.getCurrentPosition(),
                        frontleft.getCurrentPosition(),
                        backright.getCurrentPosition(),
                        frontright.getCurrentPosition());

                telemetry.update();}
        }
    }
    private void stap() {
        backleft.setPower(0);
        frontleft.setPower(0);
    }
}


