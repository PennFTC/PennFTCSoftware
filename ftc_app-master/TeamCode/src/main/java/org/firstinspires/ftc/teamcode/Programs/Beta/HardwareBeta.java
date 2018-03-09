package org.firstinspires.ftc.teamcode.Programs.Beta;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareBeta {
    public DcMotor leftfront, rightfront, leftback, rightback, lift;
    public Servo leftbottomclaw, rightbottomclaw, lefttopclaw, righttopclaw;


    public HardwareBeta() {
    }

    public void init(HardwareMap hwMap) {
        leftfront = hwMap.dcMotor.get("leftfront");
        rightfront = hwMap.dcMotor.get("rightfront");
        leftback = hwMap.dcMotor.get("leftback");
        rightback = hwMap.dcMotor.get("rightback");
        lift = hwMap.dcMotor.get("lift");
        leftbottomclaw = hwMap.servo.get("leftbottomclaw");
        rightbottomclaw = hwMap.servo.get("rightbottomclaw");
        lefttopclaw = hwMap.servo.get("leftopclaw");
        righttopclaw = hwMap.servo.get("righttopclaw");

        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}