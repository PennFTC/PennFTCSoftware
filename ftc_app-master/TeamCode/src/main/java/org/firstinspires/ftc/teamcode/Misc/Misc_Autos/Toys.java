package org.firstinspires.ftc.teamcode.Misc.Misc_Autos;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbot;
// Imports are required to import the needed information from the package to allow the functions and
// hardware devices to run as properly and as told so.
@Autonomous(name = "Toys") // Defining that this program will show up on the phone with the name
// toys, this program is the one that we use to ensure that all of our sensors are working as they should.
//@Disabled
public class Toys extends OpMode {
    HardwareShankbot bot = new HardwareShankbot(); // This imports the data from the file
    // HardwareShankbot so we can use it in this program without having to write it again
    boolean bLedOn = true; // Yes or no statement that indicates whether or not the led on the
    // color sensor will be on or not.
    boolean bLedOff = false; // Yes or no statement that indicates whether or not the led on the
    // color sensor will be on or not.
    boolean lastResetState = false;
    boolean curResetState  = false;
    @Override
    public void start() {
        bot.mRuntime.reset();
    }
    public void init() {
        bot.init(hardwareMap); // importing the hardware settings that are defined in HardwareShank
        // bot.
    }
    public void loop() { // This statement makes everything that is inside the brackets repeat until
        // it can no longer repeat.
        boolean bPrevState = false;
        boolean bCurrState = false;
        telemetry.addData("raw ultrasonic", bot.rangeSensor.rawUltrasonic());
        // This is the telemetry for the raw ultrasonic data that the range sensor gets.
        telemetry.addData("raw optical", bot.rangeSensor.rawOptical());
        // This is the telemetry for the raw optical data that the range sensor gets.
        telemetry.addData("cm", "%.2f cm",
                bot.rangeSensor.getDistance(DistanceUnit.CM));
        // This combines the data from the ultrasonic and optical data to get an accurate distance
        // measurement.
        telemetry.addData("raw ultrasonic 1", bot.rangeSensor1.rawUltrasonic());
        // This is the telemetry for the raw ultrasonic data that the second range sensor gets.
        telemetry.addData("raw optical 1", bot.rangeSensor1.rawOptical());
        // This is the telemetry for the raw optical data the second range sensor gets.
        telemetry.addData("cm 1", "%.2f cm",
                bot.rangeSensor1.getDistance(DistanceUnit.CM));
        // This combines the data from the ultrasonic and optical data  from the second range sensor to get an accurate distance measurement.
        telemetry.update();

        int rawX = bot.MRgyro.rawX();
        // This initializes the raw x axis data the gyro sensor gets
        int rawY = bot.MRgyro.rawY();
        // This initializes teh raw y axis data the gyro sensor gets
        int rawZ = bot.MRgyro.rawZ();
        // This initializes the raw z axis data the gyro sensor gets
        int heading = bot.MRgyro.getHeading();
        // This initializes the heading the gyro gets.
        int integratedZ = bot.MRgyro.getIntegratedZValue();
        // Because we have an integrating gyro we can also get teh integrated z this initializes it

       AngularVelocity rates = bot.MRgyro.getAngularVelocity(AngleUnit.DEGREES);
       // Should we wish to know the angular velocity in telemetry we have
        float zAngle = bot.MRgyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX,
                AngleUnit.DEGREES).firstAngle; // This creates the z angle so it can be formatted to
        // get the angle of the robot.
        int zAxisOffset = bot.MRgyro.getZAxisOffset(); // This creates the zoffset so it can be
        // formatted for telemetry.
        int zAxisScalingCoefficient = bot.MRgyro.getZAxisScalingCoefficient();
        // this creates the zscaling coefficient so it can be formatted for telemetry.
        telemetry.addLine()
                .addData("dx", formatRate(rates.xRotationRate))
                .addData("dy", formatRate(rates.yRotationRate))
                .addData("dz", "%s deg/s", formatRate(rates.zRotationRate));
        // These 3 pieces of telemetry allow us to know the various angle of the robot.
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