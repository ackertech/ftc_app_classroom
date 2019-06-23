package org.firstinspires.ftc.teamcode.simple;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//Functions for mecanum drive steering for autonomous and teleop
public class MecanumDrive {

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;
    public final double FACTOR = 0.9;
    public final DcMotor.RunMode currentMotorRunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final double TICKS_PER_ROTATION = 538; // TICKS (COUNTS) PER ROTATION NEEDED!!!!!!!! :)

     public int counts;

    public LinearOpMode linearOp = null;




    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }



    public MecanumDrive(DcMotor FL, DcMotor FR, DcMotor RR, DcMotor RL ) {
        frontLeftMotor = FL;      //FL is front left Motor
        frontRightMotor = FR;     // FR is front Right Motor
        rearRightMotor = RR;       //RR is back or rear right motor
        rearLeftMotor = RL;         // RL is back or rear left motor


        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setMotorRunModes(currentMotorRunMode);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //counts = counts * cpr; // added counts in here or should it be ticks



    }
    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }


    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    // Sets speed for all motors with one method
    public void setMotorSpeeds (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
    }


    // Powers Motors with no encoder counts

    public void setMotorPowerStrafeRight (double power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }

    public void setMotorPowerStrafeLeft (double power) {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);
    }

    public void setMotorPowerRotateLeft (double power) {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }

    public void setMotorPowerRotateRight (double power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);
    }



    // Powers Motors with Encoder Counts

    public void driveForward( double speed, double rotations) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);
            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                setMotorSpeeds(speed);
            }
            stopMotors();
        }


    public void driveBackward ( double speed, double rotations){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
            setMotorSpeeds(-speed);
        }
        stopMotors();
    }


    public void rotateLeft (double speed, double rotations) {
        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                frontLeftMotor.setPower(-speed);
                frontRightMotor.setPower(speed);
                rearLeftMotor.setPower(speed);
                rearRightMotor.setPower(-speed);
            }
            stopMotors();
    }

     public void rotateRight (double speed, double rotations) {
         double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
         setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         setMotorRunModes(currentMotorRunMode);

            while(frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                linearOp.telemetry.addData("current position", frontLeftMotor.getCurrentPosition());
                linearOp.telemetry.update();

                frontLeftMotor.setPower(speed);
                frontRightMotor.setPower(-speed);
                rearLeftMotor.setPower(-speed);
                rearRightMotor.setPower(speed);
            }
            stopMotors();
    }


    public void strafeRight (double speed, double rotations) {
        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                frontLeftMotor.setPower(speed);
                frontRightMotor.setPower(-speed);
                rearLeftMotor.setPower(speed);
                rearRightMotor.setPower(-speed);
            }
            stopMotors();
    }

    public void strafeLeft (double speed, double rotations) {
        double ticks = Math.abs(rotations) * (-1) *  TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                frontLeftMotor.setPower(-speed);
                frontRightMotor.setPower(speed);
                rearLeftMotor.setPower(-speed);
                rearRightMotor.setPower(speed);
            }
        stopMotors();
    }



}
