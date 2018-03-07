package org.firstinspires.ftc.teamcode.Samples.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Keatf on 3/5/2018.
 */
@Autonomous
//@Disabled
public class Sample_Encoder_Auto extends LinearOpMode {
    DcMotor leftdrive, rightdrive;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     driveSpeed             = 0.8;
    static final double     turnSpeed              = 0.5;
    public ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here

    @Override
    public void runOpMode() throws InterruptedException {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");

        leftdrive.setPower(0);
        rightdrive.setPower(0);

        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);

        leftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        encoderDrive(driveSpeed, 5, 5, 10);
        encoderDrive(turnSpeed, 5, 0, 5);
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
                telemetry.update();
            }
        }
}}
