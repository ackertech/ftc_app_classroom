package org.firstinspires.ftc.teamcode.Base.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class MechDrive implements DriveSystem  {

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;

    public final double FACTOR = 0.9;
    public final DcMotor.RunMode currentMotorRunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final double TICKS_PER_ROTATION = 538; // TICKS (COUNTS) PER ROTATION NEEDED!!!!!!!! :)
    public int counts;

    public LinearOpMode linearOp = null;

    public Orientation angles;
    public Acceleration gravity;
    public BNO055IMU imu;
    final public double SPEED = .3;

    public final double TOLERANCE = .4;


    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }

    public MechDrive() {

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

    public void strafeRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void strafeLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward ( double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveBackward ( double speed){
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
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


    public void checkAngle (double angle) {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        linearOp.telemetry.update();
        linearOp.sleep(50); //intentionally long sleep for feedback
        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                rotateRight(SPEED);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                rotateLeft(SPEED);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        stopMotors();
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }



    public void gyroReset () {
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        imu.initialize(parametersimu);
    }



}
