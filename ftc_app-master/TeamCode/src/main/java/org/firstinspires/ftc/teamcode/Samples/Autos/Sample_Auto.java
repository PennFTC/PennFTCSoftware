package org.firstinspires.ftc.teamcode.Samples.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
//@Disabled
public class Sample_Auto extends LinearOpMode {
    DcMotor leftdrive, rightdrive;

    public void runOpMode() {
        leftdrive = hardwareMap.dcMotor.get("leftdrive");
        rightdrive = hardwareMap.dcMotor.get("rightdrive");
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftdrive.setPower(0);
        rightdrive.setPower(0);

        waitForStart();
        forward();
        sleep(1000);
    }

    public void forward() {
        leftdrive.setPower(.5);
        rightdrive.setPower(.5);
    }
}
