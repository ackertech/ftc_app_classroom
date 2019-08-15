package org.firstinspires.ftc.teamcode.simple;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//Functions for mecanum drive steering for autonomous and teleop
public class Medq            }
            stopMotors();
    }

    public void strafeLeft (double speed, double rotations) {
        double ticks = Math.abs(rotations) * (-1) *  TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
               dq