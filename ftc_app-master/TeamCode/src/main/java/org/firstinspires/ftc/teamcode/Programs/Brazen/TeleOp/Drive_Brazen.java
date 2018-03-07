package org.firstinspires.ftc.teamcode.Programs.Brazen.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Programs.Brazen.HardwareBrazen;

@TeleOp
//@Disabled
public class Drive_Brazen extends OpMode {
    HardwareBrazen robot = new HardwareBrazen();
    public void init() {robot.init(hardwareMap);}
    public void loop() {
        robot.leftdrive.setPower(gamepad1.left_stick_y);
        robot.rightdrive.setPower(gamepad1.right_stick_y);
        robot.lift.setPower(gamepad2.left_stick_y);
        if (gamepad2.a) {
            robot.leftclaw.setPosition(0); robot.rightclaw.setPosition(1);
        } else {
            robot.leftclaw.setPosition(1); robot.rightclaw.setPosition(0);  }}}