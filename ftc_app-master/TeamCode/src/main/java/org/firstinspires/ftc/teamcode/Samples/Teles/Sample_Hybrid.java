package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
@Disabled
public class Sample_Hybrid extends OpMode{
    DcMotor leftfront, rightfront, leftback, rightback;
   public void start() {}
    public void init(){
        leftfront = hardwareMap.dcMotor.get("leftfront");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightback = hardwareMap.dcMotor.get("rightback");
    }
    public void loop() {
        double speed = 1;
        leftback.setPower(speed * gamepad1.left_stick_y);
        leftfront.setPower(speed * gamepad1.left_stick_y);
        rightback.setPower(speed * gamepad1.right_stick_y);
        rightfront.setPower(speed * gamepad1.right_stick_y);

        while (gamepad1.left_trigger > 0) {
            leftback.setPower(speed * gamepad1.left_trigger);
            leftfront.setPower(speed * gamepad1.left_trigger);
            rightback.setPower(-speed * gamepad1.left_trigger);
            rightfront.setPower(-speed * gamepad1.left_trigger);
        }

        while (gamepad1.right_trigger > 0) {
            leftback.setPower(-speed * gamepad1.right_trigger);
            leftfront.setPower(-speed * gamepad1.right_trigger);
            rightback.setPower(speed * gamepad1.right_trigger);
            rightfront.setPower(speed * gamepad1.right_trigger);
        }}}