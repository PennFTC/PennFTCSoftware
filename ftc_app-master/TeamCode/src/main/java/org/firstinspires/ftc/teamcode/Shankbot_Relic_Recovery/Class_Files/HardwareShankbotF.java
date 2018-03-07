package org.firstinspires.ftc.teamcode;
//////////////////////////////////////////// IMPORTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
/////////////////////////////////////// SHANKBOT COMPONENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
public class HardwareShankbotF
{
    ///////////////// MOTORS \\\\\\\\\\\\\\\
    public DcMotor  leftdrive   = null;
    public DcMotor  rightdrive  = null;
    public DcMotor up = null;
    public DcMotor spin = null;
    public DcMotor armlift = null;
    //////////////// SERVOS \\\\\\\\\\\\\\\\
    public Servo thwack = null;
    public Servo rotate = null;
    public Servo extend = null;
    public Servo lefttop;
    public Servo leftbottom;
    public Servo righttop;
    public Servo rightbottom;
    /////////////// SENSORS \\\\\\\\\\\\\\\\
    public DistanceSensor colorR = null;
    public ColorSensor colorS = null;
    public OpenGLMatrix lastLocation = null;
    public VuforiaLocalizer vuforia = null;
    public ModernRoboticsI2cRangeSensor rangeSensor = null;
    public ModernRoboticsI2cRangeSensor rangeSensor1 = null;
    public IntegratingGyroscope gyro = null;
    public ModernRoboticsI2cGyro MRgyro = null;
    HardwareMap hwMap           =  null;
    public ElapsedTime mRuntime  = new ElapsedTime();
    ///////////////////////////////////////// ENCODER DOUBLES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    /* Constructor */
    public HardwareShankbotF(){
    }
    //////////////////////////////////////// SHANKBOT CONFIG \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        // Motor Config \\
        leftdrive  = hwMap.get(DcMotor.class, "0");
        rightdrive = hwMap.get(DcMotor.class, "1");
        armlift = hwMap.get(DcMotor.class, "3");
        up = hwMap.get(DcMotor.class, "4");
        spin  = hwMap.get(DcMotor.class, "5");

        leftdrive.setPower(0);
        rightdrive.setPower(0);
        up.setPower(0);
        spin.setPower(0);
        armlift.setPower(0);

        leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        up.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        spin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightdrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftdrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        up.setDirection(DcMotorSimple.Direction.FORWARD);
        spin.setDirection(DcMotorSimple.Direction.REVERSE);
        armlift.setDirection(DcMotorSimple.Direction.REVERSE);

        //Servo Config \\
        thwack = hwMap.get(Servo.class, "0");
        rotate = hwMap.get(Servo.class, "1");
        extend = hwMap.get(Servo.class, "2");
        lefttop = hwMap.servo.get("3");
        righttop = hwMap.servo.get("4");
        leftbottom = hwMap.servo.get("5");
        rightbottom = hwMap.servo.get("6");

        thwack.setPosition(.8);
        rotate.setPosition(.5);
        extend.setPosition(1);

        // Sensor Config \\
        colorR = hwMap.get(DistanceSensor.class, "color");
        colorS = hwMap.colorSensor.get("color"); // naming the color sensor
        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r0");
        rangeSensor1 = hwMap.get(ModernRoboticsI2cRangeSensor.class, "r1");
        MRgyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)MRgyro;

        boolean lastResetState = false;
        boolean curResetState  = false;

    }
}

