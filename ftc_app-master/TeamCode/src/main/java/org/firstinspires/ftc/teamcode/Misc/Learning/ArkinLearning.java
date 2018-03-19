package org.firstinspires.ftc.teamcode.Misc.Learning;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/**
 * Created by admin2 on 3/15/2018.
 */
@TeleOp
@Disabled
public class ArkinLearning extends OpMode {
    public DcMotor leftdrive, rightdrive;

    public void start() {}


    public void init () {
        leftdrive = hardwareMap.dcMotor.get ("leftdrive");
        rightdrive = hardwareMap.dcMotor.get ("leftdrive");
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightdrive.setPower(0);
        leftdrive.setPower(0);
    }


<<<<<<< HEAD:ftc_app-master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Samples/Teles/ArkinLearning.java
    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.right_stick_y);
    }
=======
        public void loop() {
        }
>>>>>>> 131e6fd6c6831952dc395ce58f217f7f7bfea6cb:ftc_app-master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Misc/Learning/ArkinLearning.java
}