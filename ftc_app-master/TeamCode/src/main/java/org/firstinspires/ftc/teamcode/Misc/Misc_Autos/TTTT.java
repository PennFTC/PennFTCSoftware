
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/////////////////////////////////////////////////////////////////////// IMPORTS ////////////////////////////////////////////////////////////////////////////////////////////////////


@Autonomous(name="tttt")
@Disabled
public class TTTT extends LinearOpMode {
    // MOTORS //
    DcMotor leftdrive;
    DcMotor rightdrive;
    DcMotor arm;
    // SERVOS //
    Servo leftclaw;
    Servo rightclaw;
    Servo thwack;
    // SENSORS //
    DigitalChannel touch;
    ColorSensor colorS;


    // VUFORIA //


    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;
    // TIME //
    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here
    // DOUBLES //
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     driveSpeed             = 0.4;
    static final double     turnSpeed              = 0.5;
    // BOOLEANS //
    boolean bLedOn = true;
    @Override public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiTvIqzIelkSN" +
                "YCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv05LqRortLqqX4EuXv6RHPn" +
                "D//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6" +
                "wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2" +
                "gkzUVa0xIdUILpd17s8X19p3jYCquGMZLwn";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        mRuntime.reset(); // resetting the mRuntime
        // NAMING MOTORS //
        leftdrive = hardwareMap.dcMotor.get("0"); // Naming the leftdrive 0, for the 0 port
        rightdrive = hardwareMap.dcMotor.get("1"); // Naming the rightdrive 1, for the 1 port
        arm = hardwareMap.dcMotor.get("2"); // Naming the arm 2, for the 2 port
        // NAMING SERVOS //
        leftclaw = hardwareMap.servo.get("0"); // naming the leftclaw 0, for the 0 servo port
        rightclaw = hardwareMap.servo.get("1"); // naming the rightclaw 1, for the 1 servo port
        thwack = hardwareMap.servo.get("2");  // naming the thwack 2, for the 2 servo port
        // NAMING SENSORS //
        colorS = hardwareMap.colorSensor.get("color"); // naming the color sensor
        touch = hardwareMap.digitalChannel.get("touch"); // naming the touch sensor

////////////////////////////////////////////////////////////// MOTOR AND SERVO SETTINGS /////////////////////////////////////////////////////////////////////////////////////////////////

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        arm.setPower(0);

        leftdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftclaw.setPosition(1);
        rightclaw.setPosition(0);

        thwack.setPosition(0);

        leftdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        colorS.enableLed(bLedOn);
        getRuntime();

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        leftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.LEFT) {
                telemetry.addData("VuMark", "Left visible", vuMark);
                callapseArms();
                raiseArm();


                getBallColorandknockOffBallL();

            } else {
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("VuMark", "Center Visible");
                    callapseArms();
                    raiseArm();

                    getBallColorandknockOffBallC();
                } else {
                    if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        telemetry.addData("VuMark" , "Right Visible");
                        callapseArms();
                        raiseArm();

                        getBallColorandknockOffBallR();
                    } else {
                        if (vuMark == RelicRecoveryVuMark.UNKNOWN) {
                            telemetry.addData("Vumark", "I Ain't seen nothing");
                        }
                    }

                }
            }
            float[] hsvValues = new float[3];
            final float values[] = hsvValues;
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());
            telemetry.addData("Status", "Initialized");


            telemetry.update();
        }}

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {


            newLeftTarget = leftdrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightdrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftdrive.setTargetPosition(newLeftTarget);
            rightdrive.setTargetPosition(newRightTarget);


            leftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            mRuntime.reset();
            leftdrive.setPower(Math.abs(speed));
            rightdrive.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (mRuntime.seconds() < timeoutS) &&
                    (leftdrive.isBusy() && rightdrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        leftdrive.getCurrentPosition(),
                        rightdrive.getCurrentPosition());
                telemetry.update();
            }
        }
    }

    private void stap() {
        leftdrive.setPower(0);
        rightdrive.setPower(0);
        arm.setPower(0);
    }
    public void getBallColor() {
        thwack.setPosition(1);
        sleep(500);
        telemetry.addData("Color" ,colorS.blue() );
        telemetry.addData("Color" ,colorS.red() );// forcing the program not to start until the play button is pushed
        sleep(500);
        sleep(500); // making the arm that will be used to pushed the jewel off the platform go down

    }
    public void knockOffBallL() {
        if (colorS.blue() > colorS.red()) {
           // encoderDrive(.5 * driveSpeed,  -2,  -2, 0.4);  // backward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);  // if the color sensor on the arm sees the blue jewel then it moves backwards knocking the red off
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();

            stap();
            sleep(10000000);// center

        } else {
           // encoderDrive(.5 * driveSpeed,  2,  2, 0.4); // forward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();
            sleep(1000000);

        }
    }
    public void knockOffBallC() {
        if (colorS.blue() > colorS.red()) {
            // encoderDrive(.5 * driveSpeed,  -2,  -2, 0.4);  // backward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);  // if the color sensor on the arm sees the blue jewel then it moves backwards knocking the red off
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();

            stap();
            sleep(10000000);// center

        } else {
            // encoderDrive(.5 * driveSpeed,  2,  2, 0.4); // forward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();
            sleep(1000000);

        }
    }
    public void knockOffBallR() {
        if (colorS.blue() > colorS.red()) {
            // encoderDrive(.5 * driveSpeed,  -2,  -2, 0.4);  // backward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);  // if the color sensor on the arm sees the blue jewel then it moves backwards knocking the red off
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();

            stap();
            sleep(10000000);// center

        } else {
            // encoderDrive(.5 * driveSpeed,  2,  2, 0.4); // forward march
            stap();
            sleep(500);
            callapseArms();
            thwack.setPosition(0);
            sleep(500);

            leftclaw.setPosition(1);
            rightclaw.setPosition(0);
            sleep(100);
            stap();
            sleep(1000000);

        }
    }
    public void getBallColorandknockOffBallL() {
        getBallColor();
        knockOffBallL();
    }
    public void getBallColorandknockOffBallC() {
        getBallColor();
        knockOffBallC();
    }
    public void getBallColorandknockOffBallR() {
        getBallColor();
        knockOffBallR();
    }
    public void callapseArms() {
        leftclaw.setPosition(.25);
        rightclaw.setPosition(0);
        sleep(500);
    }
    public void raiseArm() {
        arm.setPower(-1);
        sleep(2000);
        stap();
    }
}
