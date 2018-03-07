package org.firstinspires.ftc.teamcode.Programs.Outreach.Bravo2017;
// this is the package that was given that allows teams to run and create programs for ftc.
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.hardware.HardwareMap;
// These are all the imports or all the specific data pieces that we use for this program.
public class HardwareBravoBot
{ // This class file contains all the hardware that is on our outreach bot the bravobot
    public DcMotor backleft = null;public DcMotor frontleft = null;
    public DcMotor backright = null;public DcMotor frontright = null;
    public DcMotor conveyor = null;public DcMotor plow = null;
    public DcMotor leftshooter = null;public DcMotor rightshooter = null;
    //Things go here
    public ElapsedTime mRuntime  = new ElapsedTime(); // Declaring time as a variable that can be
    // used.
    public HardwareBravoBot(){
    } // This is what we have to put into the various programs that would use the bravo bot hardware
    public void init(HardwareMap ahwMap) {
        // Motor stuffs
        backleft = ahwMap.dcMotor.get("0");     frontleft= ahwMap.dcMotor.get("1");
        backright= ahwMap.dcMotor.get("2");     frontright = ahwMap.dcMotor.get("3");
        conveyor = ahwMap.dcMotor.get("4");     plow = ahwMap.dcMotor.get("5");
        leftshooter = ahwMap.dcMotor.get("6");  rightshooter= ahwMap.dcMotor.get("7");
        // Setting all the config names from all the different motors that are on this robot.
        backleft.setPower(0);frontleft.setPower(0);backright.setPower(0);frontright.setPower(0);
        conveyor.setPower(0);plow.setPower(0);leftshooter.setPower(0);rightshooter.setPower(0);
        // setting the default power of all motors on the bravo bot to be 0
        backright.setDirection(DcMotorSimple.Direction.FORWARD);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        // setting the right side as forward and the left side as reverse
        backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // setting the drive motors to run without encoders.
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        plow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftshooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightshooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); }}