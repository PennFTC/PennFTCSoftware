package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name = "color")
//@Disabled
public class color extends OpMode {

    private ElapsedTime mRuntime = new ElapsedTime(); // adding in time as a variable in the program
    ColorSensor colorSensor;

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {
        colorSensor = hardwareMap.colorSensor.get("0"); // naming the color sensor 0, for the 0 sensor port
        telemetry.addData("Status", "Ready");
    }

    public void loop() {
        telemetry.addData("Status", "Run Time: " + mRuntime.toString());
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Color" ,colorSensor.blue() );
        telemetry.addData("Color" ,colorSensor.red() );
        telemetry.addData("Color" ,colorSensor.alpha() );
        telemetry.addData("Color" ,colorSensor.green() );
        telemetry.addData("Color" ,colorSensor.argb() );




        telemetry.update();
    }
}
   