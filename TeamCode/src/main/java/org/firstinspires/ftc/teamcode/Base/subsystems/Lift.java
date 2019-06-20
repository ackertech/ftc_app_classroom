package org.firstinspires.ftc.teamcode.Base.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift {

    // instance variables

    public Servo servoLift;

    public double liftExtend = 0.9;
    public double liftMid = 0.5;
    public double liftRetract = 0.10;


    // constructors

    public Lift(double extend, double retract) {

        liftExtend = extend;
        liftRetract = retract;
    }


    public void LiftExtend () {

        servoLift.setPosition(liftExtend);
    }

    public void LiftMidPoint () {

        servoLift.setPosition(liftMid);
    }

    public void LiftRetract () {

        servoLift.setPosition(liftRetract);
    }



}



