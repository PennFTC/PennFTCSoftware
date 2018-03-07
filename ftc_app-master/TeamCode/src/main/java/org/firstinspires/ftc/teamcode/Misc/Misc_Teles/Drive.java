package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Drive")
//@Disabled
public class Drive extends OpMode {
    DcMotor leftdrive;
    DcMotor rightdrive;

    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {
       leftdrive = hardwareMap.dcMotor.get("0");
       rightdrive = hardwareMap.dcMotor.get("1");
    }

    public void loop() {
        leftdrive.setPower(gamepad1.left_stick_y);
        rightdrive.setPower(gamepad1.right_stick_y);

    }
}
