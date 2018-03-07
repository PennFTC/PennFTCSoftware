package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
///////////////////////////////////////////////////////////// Rewrite //////////////////////////////////////////////////////////////////////////////////
@Autonomous(name = "Rewrite")
//@Disabled
public class Rewrite extends LinearOpMode {
    HardwareShankbot bot = new HardwareShankbot();
    boolean bLedOn = true;
    boolean bLedOff = false;
    boolean lastResetState = false;
    boolean curResetState  = false;
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() {
        bot.init(hardwareMap);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);

        telemetry.addData(">", "Calibrating Gyro");    //
        telemetry.update();
        bot.mRGyro.calibrate();
        while (!isStopRequested() && bot.mRGyro.isCalibrating())  {
           sleep(50);
           idle();
        }

        telemetry.addData(">", "Robot Ready. Press To Start");    //
        telemetry.update();

        bot.leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (!isStarted()) {
            telemetry.addData(">", "Robot Heading = %d", bot.mRGyro.getIntegratedZValue());
            telemetry.update();
        }
        bot.mRGyro.resetZAxisIntegrator();
        telemetry.update();
////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////////////////////////////////////////////////////////////////////////////////////////////////
        waitForStart();

        relicTrackables.activate();
        while (opModeIsActive()) {
            telemetry.addData("Color", bot.colorS.blue());
            telemetry.addData("Color", bot.colorS.red());
            telemetry.addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.LEFT) {
                telemetry.addData("VuMark", "Left visible", vuMark);
                standard();
                drivetoleft();
                insert();
                sleep(1012001010);
            } else {
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("VuMark", "Center Visible");
                    standard();
                    drivetocenter();
                    insert();
                    sleep(1012001010);
                } else {
                    if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        telemetry.addData("VuMark", "Right Visible");
                       standard();
                       drivetoright();
                       insert();
                       sleep(1012001010);
                    } else {
                        if (vuMark == RelicRecoveryVuMark.UNKNOWN) {
                            telemetry.addData("Vumark", "I Ain't seen nothing");
                        }
                    }
                }
                }
            }
            float[] hsvValues = new float[3];
            final float values[] = hsvValues;
            telemetry.addLine()
            .addData("Status", "Run Time: " + bot.mRuntime.toString())
            .addData("Status", "Initialized");

            telemetry.update(); }
/////////////////////////////////////////////////////////////////////////// METHODS ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void stapD() {
        bot.leftdrive.setPower(0);
        bot.rightdrive.setPower(0);
    }
 /*   private void stapA() {
        bot.arm.setPower(0);
    } */

    public void getBallColor() {
        bot.colorS.enableLed(bLedOn);
        lowerThwack();
        telemetry.addLine()
        .addData("Color", bot.colorS.blue())
        .addData("Color", bot.colorS.red())
        .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

        sleep(2000);
    }
    public void knockOffBall() {
        if (bot.colorS.blue() > bot.colorS.red()) {
            telemetry.addLine()
            .addData("Color", bot.colorS.blue())
            .addData("Color", bot.colorS.red())
            .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            driveoffstoneB();
            stapD();
            raiseThwack();
            stapD();
            sleep(500);
        } else {
            telemetry.addLine()
                    .addData("Color", bot.colorS.blue())
                    .addData("Color", bot.colorS.red())
                    .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            driveoffstoneR();
                stapD();
                raiseThwack();
                stapD();
                sleep(500);
        }
    }
    public void lowerThwack() {
        bot.thwack.setPosition(1);
        sleep(2000);
    }
    public void raiseThwack() {
        bot.thwack.setPosition(0);
        sleep(500);
    }

    public void driveoffstoneR() {
        bot.rightdrive.setPower(.1);
        bot.leftdrive.setPower(.1);
        sleep(500);
        stapD();
    }
    public void driveoffstoneB() {
        bot.rightdrive.setPower(-.1);
        bot.leftdrive.setPower(-.1);
        sleep(500);
        stapD();
    }

    public void standard() {
        getBallColor();
        knockOffBall();
        drivetowall();
        turnCCW90();
    }
    public void down() {
        bot.thwack.setPosition(.60);
        sleep(500);
    }
    public void up() {
        bot.thwack.setPosition(.2);
        sleep(500);
    }
    public void wave() {
        down();
        up();
    }
    public void insert() {
        turnCW90();
        sleep(500);
        stapD();
        forward();
        sleep(500);
        stapD();
        backward();
        sleep(500);
        stapD();
    }
    public void drivetowall()  {
        sleep(500);
        forward();
        while(bot.rangeSensor.getDistance(DistanceUnit.INCH) > 12) {
            telemetry.addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        stapD();
        sleep(500);
    }
    public void forward() {
        bot.leftdrive.setPower(.25);
        bot.rightdrive.setPower(.25);
    }
    public void backward() {
        bot.leftdrive.setPower(-.25);
        bot.rightdrive.setPower(-.25);
    }

     public void turnCW90() {
         bot.mRGyro.calibrate();
         sleep(750);
         bot.leftdrive.setPower(1);
         bot.rightdrive.setPower(-1);
         while(bot.mRGyro.getHeading() < 90) {
             telemetry.addData("heading", "%3d deg", bot.mRGyro.getHeading());
             telemetry.update(); }
         stapD();
     }
    public void turnCCW90() {
            bot.mRGyro.calibrate();
            sleep(750);
            bot.leftdrive.setPower(-1);
            bot.rightdrive.setPower(1);
            while(bot.mRGyro.getHeading() < 90) {
                telemetry.addData("heading", "%3d deg", bot.mRGyro.getHeading());
                telemetry.update(); }
            stapD();
        }

     public void drivetoleft() {
         sleep(500);
         forward();
         while(bot.rangeSensor.getDistance(DistanceUnit.INCH) < 37) {
             telemetry.addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));
             telemetry.update();
         }
         stapD();
         sleep(500);
     }
    public void drivetocenter() {
        sleep(500);
        forward();
        while(bot.rangeSensor.getDistance(DistanceUnit.INCH) < 28) {
            telemetry.addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        stapD();
        sleep(500);
    }
    public void drivetoright() {
        sleep(500);
        forward();
        while(bot.rangeSensor.getDistance(DistanceUnit.INCH) < 23) {
            telemetry.addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        stapD();
        sleep(500);
    }
}

