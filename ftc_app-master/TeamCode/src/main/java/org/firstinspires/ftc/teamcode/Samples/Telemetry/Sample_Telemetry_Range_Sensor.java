package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
//@Disabled
public class Sample_Telemetry_Range_Sensor extends OpMode{
    public ModernRoboticsI2cRangeSensor rangeSensor;
public void init() {
    rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "r0"); // naming one
}
public void loop() {
    telemetry.addData("raw ultrasonic", rangeSensor.rawUltrasonic());
    telemetry.addData("raw optical", rangeSensor.rawOptical());
    telemetry.addData("cm", "%.2f cm",
            rangeSensor.getDistance(DistanceUnit.CM));
}
}
