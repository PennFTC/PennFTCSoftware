package org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Autonomous;
// this is the package that was given that allows teams to run and create programs for ftc.
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
import org.firstinspires.ftc.teamcode.Shankbot_Relic_Recovery.Class_Files.HardwareShankbot;

// Imports are required to import the needed information from the package to allow the functions and
// hardware devices to run as properly and as told so.
///////////////////////////////////////////////////////////// Rewrite //////////////////////////////
@Autonomous(name = "Outreach, Demo") // Defining that on the phone this program will

public class Outreach_Demo extends LinearOpMode {// Defining that this file named BAuto is a autonomous
    // program or linearopmode.
    HardwareShankbot bot = new HardwareShankbot(); // This imports from the HardwareShankbot program
    // all the needed hardware devices that are on our robot.
    boolean bLedOn = true; // yes or no statement to indicate the light on the color sensor.
    boolean bLedOff = false; // yes or no statement to indicat ethe light on the color sensor.
    boolean lastResetState = false; boolean curResetState  = false;
    OpenGLMatrix lastLocation = null;   VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() {
        bot.init(hardwareMap); // importing the hardware settings that are defined in HardwareShank
        // bot.
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("came" +
                "raMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId
        );
        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiT" +
                "vIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv" +
                "05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE" +
                "5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/" +
                "F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZ" +
                "Lwn"; // entering our vuforia license key so we can properly use vuforia.
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT; // Vuforia uses a camera
        // setting the camera that it uses as the camera on the back of the phone.
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("Relic"+
                "VuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        // we need to calibrate our gyro sensor so we can get proper readings.
        telemetry.addData(">", "Robot Ready. Press To Start");  telemetry.update();
////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////
        waitForStart();
        Everything_Loop();
        relicTrackables.activate();
            }
    public void Everything_Loop() {
        while (!(1 == 2)) {
            bot.slide.setPower(1); bot.leftwheel.setPower(1); bot.rightwheel.setPower(1);
            bot.arm.setPower(1); bot.extend.setPosition(0); bot.rotate.setPosition(0);
            bot.thwack.setPosition(0);
            sleep(1000);
            bot.slide.setPower(-1); bot.leftwheel.setPower(-1); bot.rightwheel.setPower(-1);
            bot.arm.setPower(-1); bot.extend.setPosition(1); bot.rotate.setPosition(1);
            bot.thwack.setPosition(1);
            sleep(1000);
        }}}
