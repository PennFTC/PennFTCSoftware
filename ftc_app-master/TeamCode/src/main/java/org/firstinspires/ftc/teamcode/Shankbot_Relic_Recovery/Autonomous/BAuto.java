package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbotS;
@Autonomous(name = "bAuto", group = "Blue Alliance")
//@Disabled
public class BAuto extends LinearOpMode {
    HardwareShankbotS bot = new HardwareShankbotS();
    boolean bLedOn = true;
    boolean bLedOff = false;
    boolean lastResetState = false; boolean curResetState  = false;
    OpenGLMatrix lastLocation = null;   VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() {
        bot.init(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("came" +
                "raMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId
        );
        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiT" +
                "vIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv" +
                "05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE" +
                "5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/" +
                "F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZ" +
                "Lwn";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("Relic"+
                "VuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        telemetry.addData(">", "Calibrating Gyro"); telemetry.update();
        bot.MRgyro.calibrate();
        while (!isStopRequested() && bot.MRgyro.isCalibrating())  {
            sleep(50);
            idle();
        }
        telemetry.addData(">", "Robot Ready. Press To Start");  telemetry.update();
        bot.leftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.rightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (!isStarted()) {
            telemetry.addData(">", "Robot Heading = %d",
                    bot.MRgyro.getIntegratedZValue());
            telemetry.update();
        }
        bot.MRgyro.resetZAxisIntegrator();  telemetry.update();
        waitForStart();
        relicTrackables.activate();
        while (opModeIsActive()) {
            telemetry.addData("in", "%.2f in", // get the distance from the range
                    bot.rangeSensor.getDistance(DistanceUnit.INCH)); // sensor.
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.LEFT) {
                telemetry.addData("VuMark", "Left visible", vuMark);
                standard(); drivetoleft(); insert();
                sleep(1012001010);
            } else {
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("VuMark", "Center Visible");
                    standard(); drivetocenter(); insert();
                    sleep(1012001010);
                } else {
                    if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        telemetry.addData("VuMark", "Right Visible");
                        standard(); drivetoright(); insert();
                        sleep(1012001010);
                    } else {
                        if (vuMark == RelicRecoveryVuMark.UNKNOWN) {
                            telemetry.addData("Vumark", "I Ain't seen nothing");
                        }
                        }}}}
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;
        telemetry.addLine()
                .addData("Status", "Run Time: " + bot.mRuntime.toString())
                .addData("Status", "Initialized");
        telemetry.update(); }
    /////////////////////////////////////////////////////////////////////////// METHODS ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void stapD() {
        bot.leftdrive.setPower(0);  bot.rightdrive.setPower(0);
    }

    public void getBallColor() {
        bot.colorS.enableLed(bLedOn);   lowerThwack();
        telemetry.addLine()
                .addData("Color", bot.colorS.blue())
                .addData("Color", bot.colorS.red())
                .addData("in", "%.2f in",
                        bot.rangeSensor.getDistance(DistanceUnit.INCH));
        sleep(2000);}
    public void knockOffBall() {
        if (bot.colorS.blue() > bot.colorS.red()) {
            telemetry.addLine()
                    .addData("Color", bot.colorS.blue())
                    .addData("Color", bot.colorS.red())
                    .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));
            driveoffstoneB();   stapD();    raiseThwack();  stapD();
            sleep(500);
        } else {
            telemetry.addLine()
                    .addData("Color", bot.colorS.blue())
                    .addData("Color", bot.colorS.red())
                    .addData("in", "%.2f in", bot.rangeSensor.getDistance(DistanceUnit.INCH));

            driveoffstoneR();   stapD();    raiseThwack();  stapD();
            sleep(500);
        }
    }
    public void lowerThwack() {
        bot.thwack.setPosition(1);  sleep(2000);
    }
    public void raiseThwack() {
        bot.thwack.setPosition(0);  sleep(500);
    }

    public void driveoffstoneR() {
        bot.rightdrive.setPower(.1); bot.leftdrive.setPower(.1);
        sleep(500); stapD();
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
        turn180();
        sleep(500);
        forward();
        while(bot.rangeSensor.getDistance(DistanceUnit.INCH) > 12) {
            telemetry.addData("in", "%.2f in",
                    bot.rangeSensor.getDistance(DistanceUnit.INCH));
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
        bot.MRgyro.calibrate();
        sleep(750);
        bot.leftdrive.setPower(1);
        bot.rightdrive.setPower(-1);
        while(bot.MRgyro.getHeading() < 90) {
            telemetry.addData("heading", "%3d deg", bot.MRgyro.getHeading());
            telemetry.update(); }
        stapD();
    }
    public void turnCCW90() {
        bot.MRgyro.calibrate();
        sleep(750);
        bot.leftdrive.setPower(-1);
        bot.rightdrive.setPower(1);
        while(bot.MRgyro.getHeading() < 90) {
            telemetry.addData("heading", "%3d deg", bot.MRgyro.getHeading());
            telemetry.update(); }
        stapD();
    } public void turn180() {
        bot.MRgyro.calibrate();
        sleep(750);
        bot.leftdrive.setPower(-1);
        bot.rightdrive.setPower(1);
        while(bot.MRgyro.getHeading() < 180) {
            telemetry.addData("heading", "%3d deg", bot.MRgyro.getHeading());
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
            telemetry.addData("in", "%.2f in",
                    bot.rangeSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }stapD();sleep(500);
    }
    public void drivetoright() {
        sleep(500); forward();
        while(bot.rangeSensor.getDistance(DistanceUnit.INCH) < 23) {
            telemetry.addData("in", "%.2f in",
                    bot.rangeSensor.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        stapD();sleep(500); }}