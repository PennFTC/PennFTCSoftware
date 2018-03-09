package org.firstinspires.ftc.teamcode.Misc.Misc_Autos;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Keatf on 3/8/2018.
 */
@Autonomous
//@Disabled
public class Alyssa_Rube_Goldberg extends LinearOpMode{
    DcMotor motor1, motor2, motor3;
    Servo servo1;
    ModernRoboticsI2cRangeSensor rangeSensor;
    DigitalChannel buttonSensor;
    LightSensor lightSensor;
    public void runOpMode() {

        motor1 = hardwareMap.dcMotor.get("motor1");motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");servo1 = hardwareMap.servo.get("servo1");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class , "rangeSensor");
        buttonSensor = hardwareMap.get(DigitalChannel.class , "buttonSensor");
        lightSensor = hardwareMap.lightSensor.get("lightSensor");

        motor1.setPower(0); motor2.setPower(0); motor3.setPower(0);
        servo1.setPosition(0);

        waitForStart();


    }
    public void stopMotors() {
        motor1.setPower(0); motor2.setPower(0); motor3.setPower(0);
    }
}
