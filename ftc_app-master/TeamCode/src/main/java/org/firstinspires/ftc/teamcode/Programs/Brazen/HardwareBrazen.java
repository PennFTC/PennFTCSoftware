package org.firstinspires.ftc.teamcode.Programs.Brazen;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareBrazen {
    public DcMotor leftdrive, rightdrive, lift;
    public Servo leftclaw, rightclaw;

    public HardwareBrazen() {
    }

    public void init(HardwareMap hwMap) {
        leftdrive = hwMap.dcMotor.get("leftdrive");
        rightdrive = hwMap.dcMotor.get("rightdrive");
        lift = hwMap.dcMotor.get("lift");
        leftclaw = hwMap.servo.get("leftclaw");
        rightclaw = hwMap.servo.get("rightclaw");
        leftdrive.setPower(0);
        rightdrive.setPower(0);
        lift.setPower(0);
        rightdrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftclaw.setPosition(1);
        rightclaw.setPosition(0);
    }
}