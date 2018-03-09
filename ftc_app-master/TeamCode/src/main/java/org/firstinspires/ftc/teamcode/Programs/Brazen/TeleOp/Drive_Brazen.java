package org.firstinspires.ftc.teamcode.Programs.Brazen.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Programs.Brazen.HardwareBrazen;

@TeleOp
//@Disabled
public class Drive_Brazen extends OpMode {
    HardwareBrazen robot = new HardwareBrazen();
    public void init() {robot.init(hardwareMap);}
    public void loop() { double drive;
        double turn;
        double left;
        double right;
        double max;
        drive = -gamepad1.left_stick_y;
        turn = (gamepad1.left_stick_x * .8);
        left = drive + turn;
        right = drive - turn;
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }
        robot.leftdrive.setPower(left);
        robot.rightdrive.setPower(right);
        robot.lift.setPower(gamepad1.right_stick_y);

        if (gamepad1.a) {
            robot.leftclaw.setPosition(0); robot.rightclaw.setPosition(1);
        } else {
            robot.leftclaw.setPosition(1); robot.rightclaw.setPosition(0);  }}}