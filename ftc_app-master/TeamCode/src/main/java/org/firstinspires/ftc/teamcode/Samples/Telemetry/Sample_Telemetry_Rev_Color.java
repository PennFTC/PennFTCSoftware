package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
//@Disabled
public class Sample_Telemetry_Rev_Color extends OpMode{
    public DistanceSensor colorR ;
    public ColorSensor colorS;

    public void init() {
        colorR = hardwareMap.get(DistanceSensor.class, "color"); // naming the range part of
        colorS = hardwareMap.colorSensor.get("color"); // naming the color part of the color sensor to be
    }
    public void loop() {
        telemetry.addLine()
                .addData("Blue", colorS.blue())
                .addData("Red" , colorS.red())
                .addData("Green" , colorS.green())
                .addData("Distance (IN)", colorR.getDistance(DistanceUnit.INCH));
    }
}
