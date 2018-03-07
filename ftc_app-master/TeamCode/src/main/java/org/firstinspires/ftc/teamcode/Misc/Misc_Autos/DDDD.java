
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;

/////////////////////////////////////////////////////////////////////// IMPORTS ////////////////////////////////////////////////////////////////////////////////////////////////////


@Autonomous(name="dddd")
//@Disabled
public class DDDD extends LinearOpMode {
    // MOTORS //
    DcMotor leftdrive;
    DcMotor rightdrive;
    DcMotor arm;
    DcMotor relic;
    // SERVOS //
    Servo leftclaw;
    Servo rightclaw;
    Servo thwack;
    Servo rgrab;
    Servo rrotate;
    // SENSORS //
    DigitalChannel touch;
    ColorSensor colorS;
    DistanceSensor distance;
    BNO055IMU imu;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

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
    boolean bLedOff= false;
    @Override public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8a" +
                "GN23UwiTvIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7j" +
                "ZInq57jmYvzEx1Wv05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUM" +
                "HAivTV967K7E0i9QbS1OMXJE5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGC" +
                "RudADp9LUGIrLCTcPCzNkNYQGbAcGY/F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQ" +
                "nQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZLwn";

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
        relic = hardwareMap.dcMotor.get("3");
        // NAMING SERVOS //
        leftclaw = hardwareMap.servo.get("0"); // naming the leftclaw 0, for the 0 servo port
        rightclaw = hardwareMap.servo.get("1"); // naming the rightclaw 1, for the 1 servo port
        thwack = hardwareMap.servo.get("2");  // naming the thwack 2, for the 2 servo port
        rgrab = hardwareMap.servo.get("4");
        rrotate = hardwareMap.servo.get("3");
        // NAMING SENSORS //
        colorS = hardwareMap.colorSensor.get("color"); // naming the color sensor
        distance = hardwareMap.get(DistanceSensor.class, "color");
        touch = hardwareMap.digitalChannel.get("touch"); // naming the touch sensor

        telemetry.log().setCapacity(12);


        // We are expecting the IMU to be attached to an I2C port on a Core Device Interface Module and named "imu".


        BNO055IMU.Parameters parameter = new BNO055IMU.Parameters();
        parameter.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameter.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameter.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameter.loggingEnabled      = true;
        parameter.loggingTag          = "IMU";
        parameter.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameter);

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        telemetry.addData("voltage", "%.1f volts", new Func<Double>() {
            @Override public Double value() {
                return getBatteryVoltage();
            }
        });

        // Set up our telemetry dashboard

        composeTelemetry();
        telemetry.log().add("Waiting for start...");

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
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
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
            Color.RGBToHSV((int) (colorS.red() * SCALE_FACTOR),
                    (int) (colorS.green() * SCALE_FACTOR),
                    (int) (colorS.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (in)",
                    String.format(Locale.US, "%.02f", distance.getDistance(DistanceUnit.INCH)));
            telemetry.addData("Alpha", colorS.alpha());
            telemetry.addData("Red  ", colorS.red());
            telemetry.addData("Green", colorS.green());
            telemetry.addData("Blue ", colorS.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();
        }

        // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());
            telemetry.addData("Status", "Initialized");


            telemetry.update();
        }

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
                        telemetry.addData("voltage", "%.1f volts", new Func<Double>() {
                            @Override public Double value() {
                                return getBatteryVoltage();
                            }
                        }),
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
    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            gravity  = imu.getGravity();
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }
    double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){

        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
