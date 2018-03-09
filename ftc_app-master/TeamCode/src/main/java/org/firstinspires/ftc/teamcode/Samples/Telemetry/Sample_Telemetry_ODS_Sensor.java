package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
//@Disabled
public class Sample_Telemetry_ODS_Sensor extends OpMode {
    OpticalDistanceSensor odsSensor;

    public void init() {
        odsSensor = hardwareMap.opticalDistanceSensor.get("ods");
    }

    public void loop() {
        telemetry.addData("Raw", odsSensor.getRawLightDetected());
        telemetry.addData("Normal", odsSensor.getLightDetected());

    }
}
