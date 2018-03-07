package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name = "servoonastick")
@Disabled
public class servoonastick extends LinearOpMode {
    //Declaring motors

    DcMotor leftdrive;
    DcMotor rightdrive;

    DcMotor lift;
    //Declaring Servos
    Servo scissor;
    Servo grabber;
    Servo thwack;
    //Declaring sensors
    ColorSensor colorSensor;
    boolean bLedOn = true; // turning the light on, on the color sensor
    // Declaring Vuforia stuff
    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables relicimages;
    VuforiaTrackable vumark;
    private ElapsedTime mRuntime = new ElapsedTime(); // Should time be used it is added here
    static final double driveSpeed = 1.0; // creating a double for how fast the motors should go

    @Override
    public void runOpMode() throws InterruptedException {
        mRuntime.reset(); // resetting the runtime
        leftdrive = hardwareMap.dcMotor.get("0"); // Naming the leftdrive 0, for the 0 port
        rightdrive = hardwareMap.dcMotor.get("1"); // Naming the rightdrive 1, for the 1 port
      //  lift = hardwareMap.dcMotor.get("2"); // Naming the lift 2, for the 2 port

     //   scissor = hardwareMap.servo.get("0"); // naming the scissor 0, for the 0 servo port
       // grabber = hardwareMap.servo.get("1"); // naming the grabber 1, for the 1 servo port
        thwack = hardwareMap.servo.get("2");  // naming the thwack 2, for the 2 servo port

        colorSensor = hardwareMap.colorSensor.get("0"); // naming the color sensor 0, for the 0 sensor port


        leftdrive.setPower(0); // setting the leftdrive to 0 power
        rightdrive.setPower(0); // setting the rightdrive to 0 power
        thwack.setPosition(1);
       // lift.setPower(0); // setting the lift to 0 power

        leftdrive.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction of the leftdrive to Reverse
        rightdrive.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the right drive to Forward
       // lift.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the lift mechanism of the robot to forward




        colorSensor.enableLed(bLedOn); // turning on the led on the color sensor
        telemetry.update();
        getRuntime();

        waitForStart(); // forcing the program not to start until the play button is pushed
        thwack.setPosition(0);
        sleep(1000); // making the arm that will be used to pushed the jewel off the platform go down
        if (colorSensor.blue() > 0) {
            driveForwardhalfsec();
            stap();
            thwack.setPosition(1);
            sleep(1000);  // if the color sensor on the arm sees the blue jewel then it moves backwards knocking the red off
        } else {
            driveBackwardhalfsec();
            stap();
            thwack.setPosition(1);
            sleep(1000);
        }
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + mRuntime.toString());
            telemetry.addData("Status", "Initialized");
            telemetry.addData("Color" ,colorSensor.blue() );
            telemetry.update();
        }

        }





    private void driveForward() {
        leftdrive.setPower(driveSpeed);
        rightdrive.setPower(driveSpeed);
    }

    private void driveBackward() {
        leftdrive.setPower(-1 * driveSpeed);
        rightdrive.setPower(-1 * driveSpeed);
    }

    private void turnLeft() {
        leftdrive.setPower(driveSpeed);
        rightdrive.setPower(0);
    }

    private void turnRight() {
        leftdrive.setPower(0);
        rightdrive.setPower(driveSpeed);
    }
    private void driveForward1sec() {
        driveForward();
        sleep(1000);
    }
    private void driveForwardhalfsec() {
        driveForward();
        sleep(500);
    }
    private void driveBackward1sec(){
        driveBackward();
        sleep(1000);
    }
    private void driveBackwardhalfsec() {
        driveBackward();
        sleep(500);
    }
    private void turnLeft1sec() {
        turnLeft();
        sleep(1000);
    }
    private void turnLefthalfsec() {
        turnLeft();
        sleep(500);
    }
    private void turnRight1sec() {
        turnRight();
        sleep(1000);
    }
    private void turnRighthalfsec() {
        turnRight();
        sleep(500);
    }
    private void stap() {
        leftdrive.setPower(0);
        rightdrive.setPower(0);
    }


}


