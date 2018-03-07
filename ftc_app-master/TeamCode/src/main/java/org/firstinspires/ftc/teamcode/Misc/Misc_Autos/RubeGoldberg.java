package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
///////////////////////////////////////////////////////////// Rewrite //////////////////////////////////////////////////////////////////////////////////
@Autonomous(name = "RubeGoldberg")
//@Disabled
public class RubeGoldberg extends LinearOpMode {
    HardwareRubeGoldberg bot = new HardwareRubeGoldberg();
    boolean bLedOn = true;


    @Override
    public void runOpMode() {
        bot.init(hardwareMap);

////////////////////////////////////////////////////////////////////////////// RUN OPMODE //////////////////////////////////////////////////////////////////////////////////////////////////////
        waitForStart();

        if (bot.touch.getState() == true) {
            telemetry.addData("Touch" , "OH GOD THERE IS SOMETHING ON ME!");
            sleep(2000);
            bot.spin.setPower(1);
            sleep(3000);
        }
        if (bot.sC.blue() > bot.sC.red()){
            sleep(1000);
            bot.arm.setPower(1);
            sleep(2000);
        }
        if (bot.rS.getDistance(DistanceUnit.INCH) < 6) {
            bot.door.setPosition(1);
        }
    }
}