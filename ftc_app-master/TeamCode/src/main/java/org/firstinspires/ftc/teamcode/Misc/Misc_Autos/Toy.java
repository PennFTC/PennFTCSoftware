package org.firstinspires.ftc.teamcode;

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
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;
import static java.lang.Math.PI;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;


///////////////////////////////////////////////////////////// Rewrite //////////////////////////////////////////////////////////////////////////////////
@Autonomous(name = "toy")
//@Disabled
public class Toy extends LinearOpMode {
    public DcMotor  leftdrive;
    public DcMotor  rightdrive;
    public DcMotor  dump;
    public DcMotor leftwheel;
    public DcMotor rightwheel;
    public DcMotor arm;

    public Servo thwack;
    public Servo rotate;
    public Servo extend;

    public DistanceSensor colorR;
    public ColorSensor colorS;

    public ModernRoboticsI2cRangeSensor rangeSensor;
    public ModernRoboticsI2cRangeSensor rangeSensor1;
    public IntegratingGyroscope gyro;
    public ModernRoboticsI2cGyro MRgyro;
    HardwareMap hwMap            =  null;
    public ElapsedTime mRuntime  = new ElapsedTime();

    public static final double     PI                      = 3.14159268413725487692813972; // PI variable for figuring out circumference distance
    public static final double     COUNTS_PER_MOTOR_REV    = 1440 ;                        // eg: TETRIX Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;                         // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;                         // For figuring circumference
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /  (WHEEL_DIAMETER_INCHES * PI);

    boolean bLedOn = true;
    boolean bLedOff = false;
    boolean lastResetState = false;
    boolean curResetState  = false;
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        // Motor stuffs
        leftdrive  = hwMap.get(DcMotor.class, "110");
        rightdrive = hwMap.get(DcMotor.class, "1");
        dump  = hwMap.get(DcMotor.class, "2");
        arm = hwMap.get(DcMotor.class, "3");
        leftwheel = hwMap.get(DcMotor.class, "4");
        rightwheel  = hwMap.get(DcMotor.class, "5");

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        dump.setPower(0);
        leftwheel.setPower(0);
        rightwheel.setPower(0);
        arm.setPower(0);

        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dump.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        dump.setDirection(DcMotorSimple.Direction.FORWARD);
        leftwheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        //Servo Stuffs
        thwack = hwMap.get(Servo.class, "0");
        rotate = hwMap.get(Servo.class, "1");
        extend = hwMap.get(Servo.class, "2");

        thwack.setPosition(.8);
        rotate.setPosition(.5);
        extend.setPosition(1);

        //Sensor Stuffs
        colorR = hwMap.get(DistanceSensor.class, "color");
        colorS = hwMap.colorSensor.get("color"); // naming the color sensor
        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r0");
        rangeSensor1 = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r1");
        MRgyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)MRgyro;

        boolean lastResetState = false;
        boolean curResetState  = false;
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        MRgyro.calibrate();

        // Wait until the gyro calibration is complete
        mRuntime.reset();
        while (!isStopRequested() && MRgyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(mRuntime.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }

        telemetry.addData(">", "Robot Ready. Press To Start");    //
        telemetry.update();


        while (!isStarted()) {
            telemetry.addData(">", "Robot Heading = %d", MRgyro.getIntegratedZValue());
            telemetry.update();
        }
        MRgyro.resetZAxisIntegrator();
        telemetry.update();
////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////////////////////////////////////////////////////////////////////////////////////////////////
        waitForStart();
        while (opModeIsActive()) {
            boolean bLedOn = true;
            boolean bLedOff= false;
            boolean bPrevState = false;
            boolean bCurrState = false;

            int rawX = MRgyro.rawX();
            int rawY = MRgyro.rawY();
            int rawZ = MRgyro.rawZ();
            int heading = MRgyro.getHeading();
            int integratedZ = MRgyro.getIntegratedZValue();

            AngularVelocity rates = MRgyro.getAngularVelocity(AngleUnit.DEGREES);
            float zAngle = MRgyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            int zAxisOffset = MRgyro.getZAxisOffset();
            int zAxisScalingCoefficient = MRgyro.getZAxisScalingCoefficient();
            telemetry.addData("heading", "%3d deg", heading);
            telemetry.addData("integrated Z", "%3d", integratedZ);
            telemetry.addLine()
                    .addData("rawX", formatRaw(rawX))
                    .addData("rawY", formatRaw(rawY))
                    .addData("rawZ", formatRaw(rawZ));
            telemetry.addLine().addData("z offset", zAxisOffset).addData("z coeff", zAxisScalingCoefficient);
            telemetry.update();
            bPrevState = bCurrState;
            telemetry.update();
        }
    }
    String formatRaw(int rawValue) {
        return String.format("%d", rawValue);
    }

    String formatRate(float rate) {
        return String.format("%.3f", rate);
    }

    String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }}


