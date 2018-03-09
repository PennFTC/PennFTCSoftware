package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
//@Disabled
public class Sample_Telemetry_Touch_Sensor extends OpMode {
    DigitalChannel touchSensor;

    public void init() {
        touchSensor = hardwareMap.get(DigitalChannel.class, "touchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public void loop() {
        if (touchSensor.getState() == true) {
            telemetry.addData("Touch Sensor", "Not Pressed");
        } else {
            telemetry.addData("Touch Sensor", "Is Pressed");
        }
    }
}
