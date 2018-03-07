package org.firstinspires.ftc.teamcode;

//////////////////////////////////////////Imports Example\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // Here is where you set up your imports so that it knows what a motor is for instance or what
// a servo is. Java is a language where you have the ability to tell it what to do with certain
// elements. It doesn’t just know what it is which is both good and bad at times.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous(name = "WIllow Example")
public class SampleA extends LinearOpMode {
    /////////////////////////////////////////Variables Example\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
// Here is where you tell it what each variable (purple) is what hardware type
    // Motor Examples
    public DcMotor backleft = null;
    public DcMotor frontleft = null;
    public DcMotor backright = null;
    public DcMotor frontright = null;
    public DcMotor conveyor = null;
    public DcMotor plow = null;
    public DcMotor leftshooter = null;
    public DcMotor rightshooter = null;
    HardwareMap hwMap           =  null;
    // Servo Examples
    public void runOpMode() {
        // This is your config setup, here you set up what the different motors are called so that
// it knows what commands should go to what motor. Ibn your phones (get a phone) hit the 3
// dots at the top right and hit “Configuration” in the pop up menu.

// Drive Train Config
        backleft = hwMap.dcMotor.get("0");
        frontleft= hwMap.dcMotor.get("1");
        backright= hwMap.dcMotor.get("2");
        frontright = hwMap.dcMotor.get("3");
        conveyor = hwMap.dcMotor.get("4");
        plow = hwMap.dcMotor.get("5");
        leftshooter = hwMap.dcMotor.get("6");
        rightshooter= hwMap.dcMotor.get("7");

        backleft.setPower(0);
        frontleft.setPower(0);
        backright.setPower(0);
        frontright.setPower(0);
        conveyor.setPower(0);
        plow.setPower(0);
        leftshooter.setPower(0);
        rightshooter.setPower(0);

        backright.setDirection(DcMotorSimple.Direction.FORWARD);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);

        backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        conveyor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        plow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftshooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightshooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        driv();
        sleep(5000); // drive the robot forward for 5 seconds
        turnright();
        sleep(2000);// turn the robot right for 2 seaconds
        bdriv();
        sleep(2000); // back up for 2 seconds
        turnleft();
        sleep(1000); // turn the robot left for 1 second.

    }
    public void driv(){ //
        backleft.setPower(1);
        frontleft.setPower(1);
        backright.setPower(1);
        frontright.setPower(1);
    }
    public void turnleft() {
        backleft.setPower(1);
        frontleft.setPower(1);
        backright.setPower(0);
        frontright.setPower(0);
    }
    public void turnright() {
        backleft.setPower(0);
        frontleft.setPower(0);
        backright.setPower(1);
        frontright.setPower(1);
    }
    public void bdriv(){
        backleft.setPower(-1);
        frontleft.setPower(-1);
        backright.setPower(-1);
        frontright.setPower(-1);
    }
    public void stap() {
        backleft.setPower(0);
        frontleft.setPower(0);
        backright.setPower(0);
        frontright.setPower(0);
    }
}

