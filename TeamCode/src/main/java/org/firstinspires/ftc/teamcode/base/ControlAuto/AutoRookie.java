package org.firstinspires.ftc.teamcode.base.ControlAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.base.Robots.LearningBoard;


@Autonomous(name = "Auto - LabBot AWD")
//@Disabled
public class AutoRookie extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public LearningBoard rookieBoard = new LearningBoard();

   // Variables & Constants Specific to Autonomous
   public final double SPD_DRIVE_LOW = 0.38;
   public final double SPD_DRIVE_MED = 0.50;
   public final double SPD_DRIVE_HIGH = 0.75;
   public final double SPD_DRIVE_MAX = 1.0;
   public final long sleepTime = 100;


    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialization from Robot Class
        rookieBoard.init(hardwareMap);

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            rookieBoard.driveForward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Encoders");
            telemetry.update();


            rookieBoard.driveBackward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Encoders");
            telemetry.update();

            rookieBoard.rotateLeft(SPD_DRIVE_MAX, 5.0);
            sleep(1000);
            telemetry.addData("Status", "Rotate Left with Encoders");
            telemetry.update();

            rookieBoard.rotateRight(SPD_DRIVE_MAX, 5.0);
            sleep(1000);
            telemetry.addData("Status", "Rotate Right with Encoders");
            telemetry.update();

            rookieBoard.stopMotors();
            telemetry.addData("Status", "Stop All Motors");
            telemetry.update();

            rookieBoard.linearExtend(0.5);
            sleep(1000);
            rookieBoard.linearExtend(0);
            sleep(1000);
            rookieBoard.linearExtend(0.5);
            sleep(1000);
            rookieBoard.linearExtend(0);

            idle();

            requestOpModeStop();
        }

        idle();
    }
}