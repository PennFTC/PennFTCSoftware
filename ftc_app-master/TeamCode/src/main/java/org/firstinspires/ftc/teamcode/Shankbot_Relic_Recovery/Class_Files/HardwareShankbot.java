package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files;
// This is the package that we were given that contains all the required components of a ftc program
//////////////////////////////////////////// IMPORTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
/* We need imports in our program to be able to use the different functions that allow our
program to be what it is and to allow the different hardware components to work as they should. */
/////////////////////////////////////// SHANKBOT COMPONENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
public class HardwareShankbot // this is the name that we can import into other programs.
{
///////////////// MOTORS \\\\\\\\\\\\\\\
    public DcMotor leftdrive   = null; // Creatting the leftdrive of the robot and indicating that
    // it is a DcMotor.
    public DcMotor rightdrive  = null; // Creating the rightdrive of the robot and indicating that
    // it is a DcMotor.
    public DcMotor slide = null; // creating the linearslide of the robot and indicating that it is
    // a DcMotor.
    public DcMotor leftwheel = null; // creating the left collector wheel of the robot and i
    // ndicating it is a DcMotor.
    public DcMotor rightwheel = null; // creating the right collector wheel of the robot and
    // indicating it is a DcMotor.
    public DcMotor arm = null; // creating the glyph arm of the robot and indicating it is a DcMotor
//////////////// SERVOS \\\\\\\\\\\\\\\\
    public Servo thwack = null; // creating the color arm of the robot and indicating that it is a
    // Servo.
    public Servo rotate = null; // creating one of the relic servos on the robot and indicating that
    // it is a Servo.
    public Servo extend = null; // creating the other relic servo on the robot and indicating that
    // it is a servo.
/////////////// SENSORS \\\\\\\\\\\\\\\\
    public DistanceSensor colorR = null; // creating the range sensor and indicating that it is a
    // distance sensor.
    public ColorSensor colorS = null; // creating the color sensor and indicating that it is a color
    // sensor.
    public OpenGLMatrix lastLocation = null; // creating the matrix that it is used for vuforia.
    public VuforiaLocalizer vuforia = null; // creating the localizer that is need to use vuforia.
    public ModernRoboticsI2cRangeSensor rangeSensor = null; // creating one of the ultrasonic/range
    // sensors and indicating that it is a range sensor.
    public ModernRoboticsI2cRangeSensor rangeSensor1 = null; // creating one of the ultrasonic/range
    // sensors and indicating that it is a range sensor.
    public IntegratingGyroscope gyro = null; // creating the gryoscope and indicating that it is a
    // gyro.
    public ModernRoboticsI2cGyro MRgyro = null; // creating the modern robotics gyro and indicating
    // that it is a modern robotics gyro.
    HardwareMap hwMap           =  null; // shortening HardwareMap to hwMap so we don't have to type
    // as much.
    public ElapsedTime mRuntime  = new ElapsedTime(); // creating time as a variable
///////////////////////////////////////// ENCODER DOUBLES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    public static final double     COUNTS_PER_INCH         =
            (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    // setting the counts per inch to be equal to the values that have already been defined.
    /* Constructor */
    public HardwareShankbot(){}
//////////////////////////////////////// SHANKBOT CONFIG \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap; // setting hwMap as the statement that allows things to exist to the phone.
        // Motor Config \\
        leftdrive  = hwMap.get(DcMotor.class, "0"); // setting the left drive motor to be
        // named 0 to the phone.
        rightdrive = hwMap.get(DcMotor.class, "1"); // setting the right drive motor to
        // be named 1 to the phone.
        slide  = hwMap.get(DcMotor.class, "2"); // setting the linear slide motor to be
        // named 2 to the phone.
        arm = hwMap.get(DcMotor.class, "3"); // setting the glyph arm motor to be named 3
        // to tbe phone.
        leftwheel = hwMap.get(DcMotor.class, "4"); // setting the left collector wheel to
        // tbe named 4 to the phone.
        rightwheel  = hwMap.get(DcMotor.class, "5"); // setting the right collector wheel
        // to be named 5 to the phone.
        leftdrive.setPower(0); // setting the leftdrive motor to not move unless told otherwise.
        rightdrive.setPower(0); // setting the rightdrive motor to not move unless told otherwise.
        slide.setPower(0);  // setting the linear slide motor to not move unless told otherwise.
        leftwheel.setPower(0); // setting the left collector wheel to not move unless told otherwise
        rightwheel.setPower(0); // setting the right colletor wheel to not move unless told
        // otherwise.
        arm.setPower(0); // setting the glyph arm to not move unless told otherwise.
        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the left drive motor to
        // run via it's encoder.
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the right drive motor to
        // run via it's encoder.
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the linear slide motor to run
        // via it's encoder.
        leftwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the left collector wheel
        // to run via it's encoder.
        rightwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the right collector wheel
        // to run via it's encoder.
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // allowing the glyph arm to run via it's
        // encoder.
        leftdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // setting the left drive
        // motor to stop all movement when it has no power.
        rightdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // setting the right drive
        // motor to stop all movement when it has no power.
        leftdrive.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction of the
        // left drive motor to be forward.
        rightdrive.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction the the
        // right drive motor to be reverse.
        slide.setDirection(DcMotorSimple.Direction.FORWARD);  // setting the direction of the linear
        // slide motor to be forward.
        leftwheel.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the
        // left collector wheel to be forward.
        rightwheel.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the
        // right collector wheel to be reverse.
        arm.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction of the glyph
        // arm to be reverse.
        //Servo Config \\
        thwack = hwMap.get(Servo.class, "0"); // setting the color arm to be named 0 to
        // the phone.
        rotate = hwMap.get(Servo.class, "1"); // setting one of the relic servos to be
        // named 1 on the phone.
        extend = hwMap.get(Servo.class, "2"); // setting the other relic servo to be
        // named 2 on the phone.
        thwack.setPosition(.8); // setting the default position of the color arm.
        extend.setPosition(1); // setting the default position of the other relic servo.
        // Sensor Config \\
        colorR = hwMap.get(DistanceSensor.class, "color"); // naming the range part of
        // the color sensor to be named color.
        colorS = hwMap.colorSensor.get("color"); // naming the color part of the color sensor to be
        // named color.
        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r0"); // naming one
        // of the range sensors to be ne named r0.
        rangeSensor1 = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r1"); // naming the
        // other range sensor to be named r1.
        MRgyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyro"); // naming the gyro
        // sensor to be named gyro.
        gyro = (IntegratingGyroscope)MRgyro; // setting the gyro sensor to be set as a Modern
        // robotics gyro.
        boolean lastResetState = false;
        boolean curResetState  = false;
    }}