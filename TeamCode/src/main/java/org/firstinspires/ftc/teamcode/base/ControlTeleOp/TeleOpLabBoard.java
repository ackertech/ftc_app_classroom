package org.firstinspires.ftc.teamcode.base.ControlTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.base.Robots.LearningBoard;

//@Disabled
@TeleOp(name = "Learning Board - TeleOp")


public class TeleOpLabBoard extends OpMode {

    // Object Construction
    public ElapsedTime TeleOpTime = new ElapsedTime();
    public LearningBoard rookieBoard = new LearningBoard();

    // Variables & Constants specific to TeleOpMecControl
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

        rookieBoard = new LearningBoard();

        //Hardware Initialization from Robot Class
        rookieBoard.init(hardwareMap);

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

        drive();
        controlLinearExtention();
        telemetryOutput();


    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {


    }



    public void telemetryOutput() {
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

        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);


        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            rookieBoard.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            rookieBoard.rearLeftMotor.setPower(rearLeftSpeed);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            rookieBoard.rearRightMotor.setPower(rearRightSpeed);
        } else {
            rookieBoard.rearRightMotor.setPower(rearRightSpeed);
        }

    }


    public void controlLinearExtention() {
        if (gamepad1.y) {
            rookieBoard.linearExtend(0.5);
        }
        else if (gamepad1.a) {
            rookieBoard.linearRetract(0.5);
        }

    }




}
