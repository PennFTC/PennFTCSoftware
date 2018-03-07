package org.firstinspires.ftc.teamcode.Samples.Telemetry;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Keatf on 3/5/2018.
 */
@TeleOp
@Disabled
public class Sample_Telemetry_Gyro_Sensor extends OpMode{
    public IntegratingGyroscope gyro;
    public ModernRoboticsI2cGyro MRgyro;

    public void init() {
        MRgyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro"); // naming the gyro
        // sensor to be named gyro.
        gyro = (IntegratingGyroscope)MRgyro; // setting the gyro sensor to be set as a Modern
        // robotics gyro.
    }
    public void loop() {
        boolean bPrevState = false;
        boolean bCurrState = false;
        int rawX = MRgyro.rawX();
        // This initializes the raw x axis data the gyro sensor gets
        int rawY = MRgyro.rawY();
        // This initializes teh raw y axis data the gyro sensor gets
        int rawZ = MRgyro.rawZ();
        // This initializes the raw z axis data the gyro sensor gets
        int heading = MRgyro.getHeading();
        // This initializes the heading the gyro gets.
        int integratedZ = MRgyro.getIntegratedZValue();
        // Because we have an integrating gyro we can also get teh integrated z this initializes it

        AngularVelocity rates = MRgyro.getAngularVelocity(AngleUnit.DEGREES);
        // Should we wish to know the angular velocity in telemetry we have
        float zAngle = MRgyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX,
                AngleUnit.DEGREES).firstAngle; // This creates the z angle so it can be formatted to
        // get the angle of the ro
        int zAxisOffset = MRgyro.getZAxisOffset(); // This creates the zoffset so it can be
        // formatted for telemetry.
        int zAxisScalingCoefficient = MRgyro.getZAxisScalingCoefficient();
        // this creates the zscaling coefficient so it can be formatted for telemetry.
        telemetry.addLine()
                .addData("dx", formatRate(rates.xRotationRate))
                .addData("dy", formatRate(rates.yRotationRate))
                .addData("dz", "%s deg/s", formatRate(rates.zRotationRate));
        // These 3 pieces of telemetry allow us to know the various angle of the ro
        telemetry.addData("angle", "%s deg", formatFloat(zAngle)); // angle of the robot
        telemetry.addData("heading", "%3d deg", heading); // Heading value
        telemetry.addData("integrated Z", "%3d", integratedZ); // integrating gyro sensor z value
        telemetry.addLine()
                .addData("rawX", formatRaw(rawX))
                .addData("rawY", formatRaw(rawY))
                .addData("rawZ", formatRaw(rawZ));
        // These 3 pieces of telemetry allow us to get the formatted x,y, and z values.
        telemetry.addLine().addData("z offset", zAxisOffset).addData("z coeff",
                zAxisScalingCoefficient); // the z offsets and coeffs
        telemetry.update();
        bPrevState = bCurrState;
        telemetry.update();
    }


    String formatRaw(int rawValue) {return String.format("%d", rawValue);} // string values for raw data

    String formatRate(float rate) {return String.format("%.3f", rate);}

    String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }

}
