package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "Drive1")
//@Disabled
public class Drive1 extends OpMode {
    public DcMotor leftdrive  ; // Creatting the leftdrive of the robot and indicating that
    // it is a DcMotor.
    public DcMotor rightdrive ; // Creating the rightdrive of the robot and indicating that
    // it is a DcMotor.
    public DcMotor slide; // creating the linearslide of the robot and indicating that it is
    // a DcMotor.
    public DcMotor leftwheel; // creating the left collector wheel of the robot and i
    // ndicating it is a DcMotor.
    public DcMotor rightwheel; // creating the right collector wheel of the robot and
    // indicating it is a DcMotor.
    public DcMotor arm; // creating the glyph arm of the robot and indicating it is a DcMotor
    //////////////// SERVOS \\\\\\\\\\\\\\\\
    public Servo thwack; // creating the color arm of the robot and indicating that it is a
    // Servo.
    public Servo rotate; // creating one of the relic servos on the robot and indicating that
    // it is a Servo.
    public Servo extend; // creating the other relic servo on the robot and indicating that
    // it is a servo.

    @Override
    public void init() {
        // Motor Config \\
        leftdrive  = hardwareMap.get(DcMotor.class, "0"); // setting the left drive motor to be
        // named 0 to the phone.
        rightdrive = hardwareMap.get(DcMotor.class, "1"); // setting the right drive motor to
        // be named 1 to the phone.
        slide  = hardwareMap.get(DcMotor.class, "2"); // setting the linear slide motor to be
        // named 2 to the phone.
        arm = hardwareMap.get(DcMotor.class, "3"); // setting the glyph arm motor to be named 3
        // to tbe phone.
        leftwheel = hardwareMap.get(DcMotor.class, "4"); // setting the left collector wheel to
        // tbe named 4 to the phone.
        rightwheel  = hardwareMap.get(DcMotor.class, "5"); // setting the right collector wheel
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
        leftdrive.setDirection(DcMotorSimple.Direction.FORWARD); // setting the direction of the
        // left drive motor to be forward.
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE); // setting the direction the the
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
        thwack = hardwareMap.get(Servo.class, "0"); // setting the color arm to be named 0 to
        // the phone.
        rotate = hardwareMap.get(Servo.class, "1"); // setting one of the relic servos to be
        // named 1 on the phone.
        extend = hardwareMap.get(Servo.class, "2"); // setting the other relic servo to be
        // named 2 on the phone.
        thwack.setPosition(.8); // setting the default position of the color arm.
        rotate.setPosition(.5); // setting the default position of one of the relic servos.
        extend.setPosition(1); // setting the default position of the other relic servo.
    }

    public void loop() {
        // doubles //
        // Gamepad 1 //
        // floats //
        float g1LY = gamepad1.left_stick_y; // renaming the left stick y on the first gamepad to something shorter
        float g1RY = gamepad1.right_stick_y; // renaming the right stick y on the first gamepad to something shorter
        // Gamepad 2
        // floats //
        float g2LY = gamepad2.left_stick_y;  // renaming the left stick y on the second gamepad to something shorter
        float g2RY = gamepad2.right_stick_y;  // renaming the right stick y on the second gamepad to something shorter
        float g2lt = gamepad2.left_trigger; // renaming the left trigger on the second gamepad to something shorter
        float g2rt = gamepad2.right_trigger; // renaming the right trigger on the second gamepad to something shorter
        // booleans //
        boolean g2a = gamepad2.a; // Creating a boolean a for gamepad2.a to simplify code in the future
        boolean g2x = gamepad2.x; // Creating a boolean x for gamepad2.x to simplify code
        boolean g2b = gamepad2.b; // Creating a boolean b for gamepad2.b to simplify code in the future
        boolean g2rBumper = gamepad2.right_bumper; // Creating a boolean rBumper for gamepad2.right_bumper
        // to simplify code in the future
        boolean g2lBumper = gamepad2.left_bumper; // Creating a boolean lBumper for gamepad2.left_bumper
        // to simplify code in the future
        // displayed on the phone to tell us how much time has passed since we started the program.
        telemetry.addData("Status", "Initialized"); // Telemetry to say the robot is initialized.
        leftdrive.setPower(g1LY); // setting the leftdrive of the robot to have power corresponding
        // to the y axis on the left joystick on the first gamepad.
        rightdrive.setPower(g1RY); // setting the rigtdrive of the robot to have power corresponding
        // to the y axis on the right joysitck on the first gamepad.

        if (g2a) {
            leftwheel.setPower(-.8);
            rightwheel.setPower(-.8); // if we press the a button on the second gamepad than the
            // collector wheels start spinning
        } else {
            if (g2x) {
                leftwheel.setPower(.8);
                rightwheel.setPower(.8);
            } else { // if we press the x button on the second gamepad the collector wheels start spinning in the opposite direction.
                if (g2b) {
                    leftwheel.setPower(0);
                    rightwheel.setPower(0);
                } // if we press the b button on the second gamepad then the collectors wheels stop spinning
            }
        }
        slide.setPower(g2LY); // let our linear slide to have power corresponding to the left joystick's y axis on the second gamepad
        arm.setPower(g2RY); // let our glyph arm to have power corresponding to the right joystick's y axis on the second gamepad

        if (g2lBumper) {
            extend.setPosition(0);
        } else { // if we press the left bumper on the second gamepad than the servo that turns the relic up will go up
            extend.setPosition(1);
        } // if we don't press the left bumper on the second gamepad than the servo that turn the relic up will not go up.

        if (g2rBumper) {
            rotate.setPosition(0);
        } else { // we press the right bumper on the second gamepad than the servo that grabs the relic will close.
            rotate.setPosition(1);
        } // we don't press the right bumper on the second gamepad than the servo that grabs the relic will not close.
        if (gamepad2.left_stick_button) {
            thwack.setPosition(0);
        } else { // if we press down on the left stick on the second gamepad than the color arm lowers.
            thwack.setPosition(.8);
        }  // if we don't press down on the left stick on the second gamepad than the color arm stays where it is.
    }
}



