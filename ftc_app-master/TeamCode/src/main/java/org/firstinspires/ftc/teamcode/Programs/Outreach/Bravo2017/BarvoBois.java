package org.firstinspires.ftc.teamcode.Programs.Outreach.Bravo2017;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "2017Bravo")
//@Disabled
public class BarvoBois extends OpMode {
    public ElapsedTime mRuntime = new ElapsedTime();
    HardwareBravoBot bot = new HardwareBravoBot();

    @Override
    public void start() {
        mRuntime.reset();
    }

    public void init() {
        bot.init(hardwareMap);
    }

    public void loop() {
        telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // telemetry
        bot.backleft.setPower(1 * gamepad1.left_stick_y);
        bot.frontleft.setPower(1 * gamepad1.left_stick_y);
        bot.backright.setPower(1 * gamepad1.right_stick_y);
        bot.frontright.setPower(1 * gamepad1.right_stick_y);
        bot.leftshooter.setPower(1 * gamepad1.right_trigger);
        bot.rightshooter.setPower(-1 * gamepad1.right_trigger);
        bot.leftshooter.setPower(-1 * gamepad1.left_trigger);
        bot.rightshooter.setPower(1 * gamepad1.left_trigger);
        if (gamepad1.right_bumper) {
            bot.conveyor.setPower(1);
        } else {
            bot.conveyor.setPower(0);
            if (gamepad1.left_bumper) {
                bot.conveyor.setPower(-1);
            } else {
                bot.conveyor.setPower(0);
            }
            if (gamepad1.a) {
                bot.plow.setPower(1);
            } else {
                bot.plow.setPower(0);
                if (gamepad1.b) {
                    bot.plow.setPower(-1);
                } else {
                    bot.plow.setPower(0);
                }
            }
        }
    }
}