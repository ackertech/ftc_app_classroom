package org.firstinspires.ftc.teamcode.Base.competition;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Base.robot.LabBot;


@Autonomous(name = "Auto - LabBot")
//@Disabled
public class AutoTest extends LinearOpMode {


   private ElapsedTime runtime = new ElapsedTime();

   final long sleepTime = 100;
   LabBot myBot;


    @Override
    public void runOpMode() throws InterruptedException {

        myBot.init(hardwareMap);

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            myBot.myLift.LiftExtend();

            myBot.driveForward(myBot.SPD_DRIVE_MAX);
            sleep(1000);

            myBot.myLift.LiftRetract();
            sleep(1000);

            myBot.stopMotors();
            sleep(1000);

            myBot.driveForward(myBot.SPD_DRIVE_MAX,2.0);
            sleep(1000);

            myBot.driveBackward(myBot.SPD_DRIVE_MAX,2.0);
            sleep(1000);

            myBot.strafeLeft(myBot.SPD_DRIVE_MAX,2.0);
            sleep(1000);

            myBot.strafeRight(myBot.SPD_DRIVE_MAX,2.0);
            sleep(1000);

            myBot.stopMotors();

            myBot.checkAngle(90);
            sleep(1000);

            myBot.checkAngle(180);
            sleep(1000);

            myBot.checkAngle(270);
            sleep(1000);

            myBot.checkAngle(358);
            sleep(1000);

            idle();

            requestOpModeStop();
        }

        idle();
    }
}