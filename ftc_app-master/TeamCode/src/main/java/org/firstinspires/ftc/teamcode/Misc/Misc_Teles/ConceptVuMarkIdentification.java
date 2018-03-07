import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;



@Autonomous(name="Concept: VuMark Id", group ="Concept")
@Disabled
public class ConceptVuMarkIdentification extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AS0UfjX/////AAAAGRskp0HfSUnsoynVbGYPNsdMokODK2h8aGN23UwiTvIqzIelkSNYCun2eZtoII5VtMXdkhgzhrrxXSpSDB7bkpSWNBNCSykmHOj8LIA7jZInq57jmYvzEx1Wv05LqRortLqqX4EuXv6RHPnD//44rNVLuT3pJBy7tMSn8p8Snzics+YUMHAivTV967K7E0i9QbS1OMXJE5fasIg3XX/3LnWhHeeTRwEYHA9M7ENUyoJ6wZzq7xHwvQxcGCRudADp9LUGIrLCTcPCzNkNYQGbAcGY/F1U5KosGNNh/GXFsJKMbV2kK7vDTgmaVvgk7YVkOQnQIiB/2gkzUVa0xIdUILpd17s8X19p3jYCquGMZLwn";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */

            }

            telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
