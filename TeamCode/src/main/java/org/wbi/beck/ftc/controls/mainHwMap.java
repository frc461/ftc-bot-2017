package org.wbi.beck.ftc.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class mainHwMap {
    HardwareMap hwMap = null;

    public DcMotor RDrive1 = null;
    public DcMotor RDrive2 = null;
    public DcMotor LDrive1 = null;
    public DcMotor LDrive2 = null;
    public DcMotor Shooter = null;
    public DcMotor Intake = null;
    public DcMotor Lift = null;

    private ElapsedTime period  = new ElapsedTime();




    public mainHwMap(){

    }

    public void init(HardwareMap hwMap_working){
        hwMap = hwMap_working;

        LDrive1 = hwMap.dcMotor.get("L Drive 1");
        LDrive2 = hwMap.dcMotor.get("L Drive 2");
        RDrive1 = hwMap.dcMotor.get("R Drive 1");
        RDrive2 = hwMap.dcMotor.get("R Drive 2");
        Shooter = hwMap.dcMotor.get("Shooter");
        Intake = hwMap.dcMotor.get("Intake");
        Lift = hwMap.dcMotor.get("Lift");

        Shooter.setDirection(DcMotor.Direction.REVERSE);
        LDrive1.setDirection(DcMotor.Direction.FORWARD);
        LDrive2.setDirection(DcMotor.Direction.FORWARD);
        RDrive1.setDirection(DcMotor.Direction.FORWARD);
        RDrive2.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.FORWARD);
        Lift.setDirection(DcMotorSimple.Direction.FORWARD);

        LDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
