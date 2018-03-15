package org.firstinspires.ftc.teamcode.Samples.Teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/**
 * Created by admin2 on 3/15/2018.
 */
@TeleOp
@Disabled
public class ArkinLearning extends OpMode {
    public DcMotor leftdrive, rightdrive;

    public void start() {}


    public void init () {
        leftdrive = hardwareMap.dcMotor.get ("leftdrive");}


        public void loop() {

}}