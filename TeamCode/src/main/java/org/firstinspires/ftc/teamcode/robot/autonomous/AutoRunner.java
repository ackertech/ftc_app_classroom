package org.firstinspires.ftc.teamcode.robot.autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Gyro;


@Autonomous(name = "Auto Runner")
//@Disabled
public class AutoRunner extends LinearOpMode {

    MecanumDrive myMechDrive;
    Gyro myGyro;
    Paths myPaths;


    private ElapsedTime runtime = new ElapsedTime();
    private static final float mmPerInch = 25.4f;
    private static final float mmFTCFieldWidth = (12 * 6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
    private static final float mmTargetHeight = (6) * mmPerInch;          // the height of the center of the target image above the floor


    boolean targetVisible;

    @Override
    public void runOpMode() throws InterruptedException {


        final long sleepTime = 100;
        final double SPD_DRIVE_MED = .5;


        myMechDrive = new MecanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));
        myMechDrive.setLinearOp(this);

        myGyro = new Gyro(hardwareMap.get(BNO055IMU.class, "imu"));
        myGyro.setLinearOp(this);

        myPaths = new Paths();
        myPaths.setLinearOp(this);

        waitForStart();

        idle();

        while (opModeIsActive()) {

            sleep(100);
            idle();

            myPaths.DriveTest(myGyro, myMechDrive);

            sleep(sleepTime);
            idle();


            requestOpModeStop();
        }

        idle();
    }
}