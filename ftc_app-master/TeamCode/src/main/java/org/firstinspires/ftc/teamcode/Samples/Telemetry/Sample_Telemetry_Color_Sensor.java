package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp
//@Disabled
public class Sample_Telemetry_Color_Sensor extends OpMode{
    ColorSensor colorSensor;
    public void init() {
        colorSensor =hardwareMap.colorSensor.get("colorSensor");}
    public void loop(){
        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;

        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);

        boolean bPrevState = false;
        boolean bCurrState = false;

        boolean bLedOn = true;

        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        colorSensor.enableLed(bLedOn);

            // check the status of the x button on either gamepad.
            bCurrState = gamepad1.x;

            // check for button state transitions.
            if ((bCurrState == true) && (bCurrState != bPrevState))  {

                // button is transitioning to a pressed state. So Toggle LED
                bLedOn = !bLedOn;
                colorSensor.enableLed(bLedOn);
            }

            // update previous state variable.
            bPrevState = bCurrState;

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();
        }
    }
