package org.firstinspires.ftc.teamcode.simple;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;




//@Disabled
@TeleOp(name = "TeleOpTest - Simple Runner")

public class TeleOpRunner extends OpMode {

    // left stick y axis controls forward/backward rotation of left motors
    // right stick y axis controls forward/backward rotation of right motors (tank drive)
    // left/right triggers control strafing left/right

    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor rearLeftMotor;
    DcMotor rearRightMotor;

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
    public double endGameTime = 90000;
    public double hangTime = 108000;

    boolean LeftBumber = false;


    @Override
    public void init() {

        //map  & set up devices.
        frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor");
        frontRightMotor = hardwareMap.dcMotor.get("front_right_motor");
        rearLeftMotor = hardwareMap.dcMotor.get("rear_left_motor");
        rearRightMotor = hardwareMap.dcMotor.get("rear_right_motor");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


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

        //reverse mode - reverse DRIVE CONTROL motors.
        reverseMode();

        //Speed Toggle for driver 1 control (Full Speed or Reduced Speed)
        speedToggle();

        //drive robot
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

            frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
            rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

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
                frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                frontLeftMotor.setPower(frontLeftSpeed * speedMultiplier);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                frontRightMotor.setPower(frontRightSpeed);
            } else {
                frontRightMotor.setPower(frontRightSpeed * speedMultiplier);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                rearLeftMotor.setPower(rearLeftSpeed * speedMultiplier);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                rearRightMotor.setPower(rearRightSpeed);
            } else {
                rearRightMotor.setPower(rearRightSpeed * speedMultiplier);
            }
        }

        else {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);
            frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
            rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

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
                frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                frontLeftMotor.setPower(frontLeftSpeed * speedMultiplier);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                frontRightMotor.setPower(frontRightSpeed);
            } else {
                frontRightMotor.setPower(frontRightSpeed * speedMultiplier);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                rearLeftMotor.setPower(rearLeftSpeed * speedMultiplier);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                rearRightMotor.setPower(rearRightSpeed);
            } else {
                rearRightMotor.setPower(rearRightSpeed * speedMultiplier);
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