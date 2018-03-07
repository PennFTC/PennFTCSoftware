package org.firstinspires.ftc.teamcode.Programs.Outreach.Holonomibot;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
// These are the imports that we use in this program.
public class HardwareHolonomibot
{ // This class file is one that includes all the hardware what is present on on outreach bot the
    // holonomibot.
    public DcMotor  leftfront   = null; // Declaring we have a dcmotor named leftfront
    public DcMotor  rightfront  = null; // Declaring we have a dcmotor named rightfront
    public DcMotor  leftrear = null; // Declaring we have a dcmotor named lefrear
    public DcMotor  rightrear = null; // Declaring we have a dcmotor named rightrear
    public ElapsedTime mRuntime  = new ElapsedTime(); // Declaring time as a usable variable.
    /* Constructor */
    public HardwareHolonomibot(){
    } // This is what needs to be imported into any program that would run the holonomibot.
    public void init(HardwareMap ahwMap) {
        // Motor stuffs
        leftfront  = ahwMap.get(DcMotor.class, "0"); // sets the motor named leftfront
        // to be named 0 in the config for the phone
        rightfront = ahwMap.get(DcMotor.class, "1"); // sets the motor named rightfront
        // to be named 1 in the config for the phone
        leftrear = ahwMap.get(DcMotor.class, "2"); // sets the motor named leftrear to be
        // named 2 in the config for the phone
        rightrear  = ahwMap.get(DcMotor.class, "3"); // sets the motor named rightrear to
        // be named 3 in the config for the phone
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftrear.setPower(0);
        rightrear.setPower(0);
        // sets the default power of all drive motors to 0
        leftfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftrear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightrear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Sets all the drive motors to drive without using encoders.
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Sets all the drive motors to stop all motion when there is no power.
        leftfront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftrear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightrear.setDirection(DcMotorSimple.Direction.REVERSE);
        // Sets the direction of the left motors to forward and the right motors to reverse.
    }}