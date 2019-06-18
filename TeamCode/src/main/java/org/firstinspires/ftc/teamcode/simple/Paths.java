package org.firstinspires.ftc.teamcode.simple;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class Paths {

    public Paths() {

    }

    public LinearOpMode linearOp = null;

    // created constant variables that are used for speed (different setting)

    final double SPD_DRIVE_LOW = .38;                  //Lowest speed
    final double SPD_DRIVE_MED = 0.5;   //was .6 but for testing we slowed it down
    final double SPD_DRIVE_HIGH = .75; // was .8 but for testing we slowed it down
    final double SPD_DRIVE_MAX = 1.0;
    final double SPD_ARM_MED = .5;
    final long sleepTime = 50;



    public void setLinearOp(LinearOpMode Op) {
        linearOp = Op;
    }


    public void DriveTest (MecanumDrive myMechDrive) {

        myMechDrive.driveForward(SPD_DRIVE_HIGH, 3);
        linearOp.sleep(sleepTime);

        myMechDrive.driveBackward(SPD_DRIVE_HIGH, 3);
        linearOp.sleep(sleepTime);

        myMechDrive.rotateLeft(SPD_DRIVE_HIGH, 1);
        linearOp.sleep(sleepTime);

        myMechDrive.rotateRight(SPD_DRIVE_HIGH, 1);
        linearOp.sleep(sleepTime);

        myMechDrive.strafeRight(SPD_DRIVE_MED, 3);
        linearOp.sleep(sleepTime);

        myMechDrive.strafeRight(SPD_DRIVE_MED, 3);
        linearOp.sleep(sleepTime);

        myMechDrive.stopMotors();
        linearOp.sleep(sleepTime);


    }



}
