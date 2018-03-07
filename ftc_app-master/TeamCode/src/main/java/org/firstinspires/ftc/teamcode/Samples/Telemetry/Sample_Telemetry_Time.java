package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
//Disabled
public class Sample_Telemetry_Time extends OpMode{
    public ElapsedTime mRuntime = new ElapsedTime();
    public void init() {}
    public void loop() {
        telemetry.addData("Time", mRuntime);
    }
}
