package org.firstinspires.ftc.teamcode.Misc.Learning;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

    @Autonomous
//@Disabled
    public class LearningAuto extends LinearOpMode {
        DcMotor leftdrive, rightdrive;

        public void runOpMode() {
            leftdrive = hardwareMap.dcMotor.get("leftdrive");
            rightdrive = hardwareMap.dcMotor.get("rightdrive");
            rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
            leftdrive.setPower(0);
            rightdrive.setPower(0);

            waitForStart();
            forward5 ();
            turn_right ();
            backward2 ();
            turn_left ();
            forward1 ();
            turn_right10 ();

        }
        public void forward5() {
            leftdrive.setPower(1);
            rightdrive.setPower(1);
            sleep(5000);
}

        public void turn_right () {
            leftdrive.setPower(1);
            rightdrive.setPower(-1);
            sleep (1000);
        }
       public void backward2 () {
            leftdrive.setPower(-1);
            rightdrive.setPower(-1);
            sleep (2000);
    }   public void turn_left () {
            leftdrive.setPower(-1);
            rightdrive.setPower(1);
            sleep (1000);
    }   public void forward1 () {
            leftdrive.setPower(1);
            rightdrive.setPower(1);
            sleep (1000);
    }   public void turn_right10 () {
            leftdrive.setPower(1);
            rightdrive.setPower(-1);
            sleep (10000);
    }}

