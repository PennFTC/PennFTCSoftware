package org.firstinspires.ftc.teamcode.Programs.Bravo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareBravo {
    public DcMotor leftfront, rightfront, leftback, rightback, lift;
    public Servo leftclaw, rightclaw;

    public HardwareBravo() {
    }

    public void init(HardwareMap hwMap) {
        leftfront = hwMap.dcMotor.get("leftfront");
        rightfront = hwMap.dcMotor.get("rightfront");
        leftback = hwMap.dcMotor.get("leftback");
        rightback = hwMap.dcMotor.get("rightback");
        lift = hwMap.dcMotor.get("lift");
        leftclaw = hwMap.servo.get("leftclaw");
        rightclaw = hwMap.servo.get("rightclaw");
    }
}