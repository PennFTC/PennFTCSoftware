package org.firstinspires.ftc.teamcode.Programs.Bermuda;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class HardwareBermuda { // hi
    public DcMotor leftfront, rightfront, leftback, rightback;
    public HardwareBermuda() {}
    public void init(HardwareMap hwMap) {
        leftfront = hwMap.dcMotor.get("leftfront"); rightfront = hwMap.dcMotor.get("rightfront");
        leftback = hwMap.dcMotor.get("leftback");   rightback = hwMap.dcMotor.get("rightback");
        leftfront.setPower(0);  rightfront.setPower(0);
        leftback.setPower(0);   rightback.setPower(0);
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);    }}