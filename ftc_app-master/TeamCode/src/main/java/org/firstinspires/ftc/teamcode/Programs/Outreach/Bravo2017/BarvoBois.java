package org.firstinspires.ftc.teamcode.Programs.Outreach.Bravo2017;
// this is the package that was given that allows teams to run and create programs for ftc.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
// These are all the imports that are used in this program
@TeleOp (name= "2017Bravo") // This is the drive program for our bravo bot
@Disabled
public class BarvoBois extends OpMode {
    public ElapsedTime mRuntime = new ElapsedTime();
    HardwareBravoBot bot = new HardwareBravoBot();
    @Override
    public void start() {
        mRuntime.reset();}
    public void init() {
        bot.init(hardwareMap);
    }
    public void loop() {
        telemetry.addData("Status", "Run Time: " + mRuntime.toString()); // telemetry
        // that gets the amount of time that the robot has been running.
        bot.backleft.setPower(1 * gamepad1.left_stick_y);
        bot.frontleft.setPower(1 * gamepad1.left_stick_y);
        // setting the left side of the robot to drive based of off the position of the left
        // joystick
        bot.backright.setPower(1 * gamepad1.right_stick_y);
        bot.frontright.setPower(1 * gamepad1.right_stick_y);
        // setting the right side of the robot to drive based of off the position of the right
        // joystick
        bot.leftshooter.setPower(1 * gamepad1.right_trigger);
        bot.rightshooter.setPower(-1 * gamepad1.right_trigger);
        bot.leftshooter.setPower(-1 * gamepad1.left_trigger);
        bot.rightshooter.setPower(1 * gamepad1.left_trigger);
        // set the power of the shooters to be bound to the triggers, with the right stick being to
        // short forward and the left stick to bring it back should it need to.
        if (gamepad1.right_bumper) {
            bot.conveyor.setPower(1);} else {
            bot.conveyor.setPower(0);
            if (gamepad1.left_bumper) {
                bot.conveyor.setPower(-1);}else {
                bot.conveyor.setPower(0);
            } // setting the power of the conveyor to be bound to the left and right bumpers if we
            // push the right bumper it brings the balls to the shooter and if we have to the left
            // takes them down.
            if (gamepad1.a) {
                bot.plow.setPower(1);} else {
                bot.plow.setPower(0);
                if (gamepad1.b) {
                    bot.plow.setPower(-1);}else {
                    bot.plow.setPower(0);
                } // setting the power of the plow or what brings the balls into the robot to be
                // bound to the a and b buttons, if we push the a button balls go in and if we push
                // the b button balls are taken out.
                }}}}