package org.firstinspires.ftc.teamcode.Base.competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.robot.LabBot;


//@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp - LabBot")

public class TeleOp extends OpMode {

    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    boolean reverseModeToggle;
    double speedMultiplier = 1.0;

    boolean initTeleOpToggle;

    double powerThreshold = 0;

    public ElapsedTime TeleOpTime;

    LabBot myBot;


    @Override
    public void init() {

        //map  & set up devices.
        myBot.init(hardwareMap);


        TeleOpTime = new ElapsedTime();

        //set initial toggles
        reverseModeToggle = true;
        initTeleOpToggle = true;
    }

    @Override
    public void loop() {

        if (initTeleOpToggle) {
            initTeleOp();
        }

        reverseMode();

        speedToggle();

        drive();

        //output telemetry
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


        telemetry.update();

    }

    // resets & initializes the servo positions for the Intake Rotator and Lander Scorer

    public void initTeleOp() {
        TeleOpTime.reset();
        initTeleOpToggle = false;               // false so initializes only once
    }



    public void drive () {

        if (reverseModeToggle) {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = -gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);

            myBot.frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            myBot.rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            myBot.frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
            myBot.rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

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
                myBot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                myBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiplier);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                myBot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                myBot.frontRightMotor.setPower(frontRightSpeed * speedMultiplier);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                myBot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                myBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiplier);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                myBot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                myBot.rearRightMotor.setPower(rearRightSpeed * speedMultiplier);
            }
        }

        else {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);

            myBot.frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            myBot.rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            myBot.frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
            myBot.rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

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
                myBot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                myBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiplier);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                myBot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                myBot.frontRightMotor.setPower(frontRightSpeed * speedMultiplier);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                myBot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                myBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiplier);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                myBot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                myBot.rearRightMotor.setPower(rearRightSpeed * speedMultiplier);
            }
        }
    }

    //Reverse mode for driver control
    public void reverseMode () {
        if (gamepad1.dpad_up) {    //see if the controller is in reverse mode or not (if joysticks are pressed down or not)
            reverseModeToggle = false; // forward mode
        }

        else if (gamepad1.dpad_down) {
            reverseModeToggle = true;    //reverse mode
        }
    }

    // Speed Toggle for Driver 1.  Allows Driver to shift between full speed and reduced speed using bumpers
    public void speedToggle () {
        if (gamepad1.left_bumper) {
            speedMultiplier = 1.0;
        }

        else if (gamepad1.right_bumper) {
            speedMultiplier = 0.60;
        }
    }

}