package org.firstinspires.ftc.teamcode.base.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.base.robot.LabBot;


@Autonomous(name = "Auto - LabBot")
//@Disabled
public class AutoTest extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public LabBot AckerBot = new LabBot();

   // Variables & Constants Specific to Autonomous
   public final double SPD_DRIVE_LOW = 0.38;
   public final double SPD_DRIVE_MED = 0.50;
   public final double SPD_DRIVE_HIGH = 0.75;
   public final double SPD_DRIVE_MAX = 1.0;
   public final long sleepTime = 100;


    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialization from Robot Class
        AckerBot.init(hardwareMap);

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            AckerBot.HoodOpen();
            telemetry.addData("Status", "Hood Opened");
            telemetry.update();
            sleep(2000);

            AckerBot.HoodClose();
            telemetry.addData("Status", "Hood Closed");
            telemetry.update();
            sleep(2000);

            AckerBot.driveForward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Encoders");
            telemetry.update();

            AckerBot.driveBackward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Encoders");
            telemetry.update();

            AckerBot.rotateLeft(SPD_DRIVE_MAX, 5.0);
            sleep(1000);
            telemetry.addData("Status", "Rotate Left with Encoders");
            telemetry.update();

            AckerBot.rotateRight(SPD_DRIVE_MAX, 5.0);
            sleep(1000);
            telemetry.addData("Status", "Rotate Right with Encoders");
            telemetry.update();

            AckerBot.strafeLeft(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Left with Encoders");
            telemetry.update();

            AckerBot.strafeRight(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Right with Encoders");
            telemetry.update();

            AckerBot.stopMotors();
            telemetry.addData("Status", "Stop All Motors");
            telemetry.update();
/**
            //AckerBot.checkAngle(90);
            //sleep(1000);

            //AckerBot.checkAngle(180);
            //sleep(1000);

            //AckerBot.checkAngle(270);
            //sleep(1000);

            //AckerBot.checkAngle(358);
            //sleep(1000);
**/
            idle();

            requestOpModeStop();
        }

        idle();
    }
}