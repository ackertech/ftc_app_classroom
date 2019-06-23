package org.firstinspires.ftc.teamcode.base.competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.base.robot.LabBot;


//@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp - LabBot")

public class TeleOp extends OpMode {

    // Object Construction
    public ElapsedTime TeleOpTime = new ElapsedTime();
    public LabBot AckerBot = new LabBot();

    // Variables & Constants specific to TeleOp
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;


    // Runs ONCE when driver presses INIT
    @Override
    public void init() {

        AckerBot = new LabBot();

        //Hardware Initialization from Robot Class
        AckerBot.init(hardwareMap);

        TeleOpTime = new ElapsedTime();

    }

    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() {

    }

    // Runs ONCE when driver presses PLAY
    @Override
    public void start() {
    }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        controlHood();

        motorTest();

        drive();

        telemetryOutput();


    }


    public void telemetryOutput() {
        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
        telemetry.addData("Left joystick Y (gp2): ", gamepad2.left_stick_y);
        telemetry.addData("Right joystick Y (gp2): ", gamepad2.right_stick_y);
        telemetry.update();

    }


    public void drive () {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);

            frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
            frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

            frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
            frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

            rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
            rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

            rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
            rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

            if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
                frontLeftSpeed = 0;
                AckerBot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                AckerBot.frontLeftMotor.setPower(frontLeftSpeed);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                AckerBot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                AckerBot.frontRightMotor.setPower(frontRightSpeed);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                AckerBot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                AckerBot.rearLeftMotor.setPower(rearLeftSpeed);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                AckerBot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                AckerBot.rearRightMotor.setPower(rearRightSpeed);
            }

    }


    public void controlHood() {
        if (gamepad1.y) {
            AckerBot.HoodOpen();
        }
        else if (gamepad1.a) {
            AckerBot.HoodClose();
        }
    }

    public void motorTest() {
        if (gamepad1.x) {
            AckerBot.driveBackward(1.0);

        }
        else if (gamepad1.b) {
            AckerBot.setFrontLeftPower(1.0);
        }
    }

}