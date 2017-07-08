package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  LDrive1 = null;
    public DcMotor  LDrive2 = null;
    public DcMotor  RDrive1 = null;
    public DcMotor  RDrive2 = null;
    public DcMotor  Intake = null;
    public DcMotor  Shooter1    = null;
    public DcMotor  Shooter2    = null;
    public Servo  RServo1 = null;
    public Servo  RServo2 = null;
    public Servo  LServo1 = null;
    public Servo  LServo2 = null;
    public DcMotor Lift = null;
    public Servo BallStop = null;
    public Servo BallHinge = null;
    

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;


    //Servo Positioning
    public static final double HOOD_HOME = 0.2;
    public static final double HOOD_MIN = 0;
    public static final double HOOD_MAX = 1;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        LDrive1   = hwMap.dcMotor.get("L Drive 1");
        LDrive2 = hwMap.dcMotor.get("L Drive 2");
        RDrive1 = hwMap.dcMotor.get("R Drive 1");
        RDrive2  = hwMap.dcMotor.get("R Drive 2");
        Intake = hwMap.dcMotor.get("Intake");
        Shooter1 = hwMap.dcMotor.get("Shooter 2");
        Shooter2 = hwMap.dcMotor.get("Shooter 2");
        RServo1 = hwMap.servo.get("R Servo 1");
        RServo2 = hwMap.servo.get("R Servo 2");
        LServo1 = hwMap.servo.get("L Servo 1");
        LServo2 = hwMap.servo.get("L Servo 2");
        Lift = hwMap.dcMotor.get("Lift");
        BallStop = hwMap.servo.get("Ball Stop");
        BallHinge = hwMap.servo.get("Ball Hinge");
        //armMotor    = hwMap.dcMotor.get("left_arm");
        LDrive1.setDirection(DcMotor.Direction.FORWARD);
        LDrive2.setDirection(DcMotor.Direction.REVERSE);
        RDrive1.setDirection(DcMotor.Direction.REVERSE);
        RDrive2.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.FORWARD);
        //intake.setDirection(DcMotor.Direction.FORWARD);
        Shooter1.setDirection(DcMotor.Direction.FORWARD);
        //Shooter2.setDirection(DcMotor.Direction.REVERSE);
        LServo1.setDirection(Servo.Direction.REVERSE);
        LServo2.setDirection(Servo.Direction.REVERSE);
        RServo1.setDirection(Servo.Direction.FORWARD);
        RServo2.setDirection(Servo.Direction.FORWARD);
        Lift.setDirection(DcMotor.Direction.FORWARD);
        BallStop.setDirection(Servo.Direction.FORWARD);
        BallHinge.setDirection(Servo.Direction.FORWARD);
        BallStop.scaleRange(0, 0.5);
        LServo1.scaleRange(-1, 0.7);
        LServo2.scaleRange(-1, 0.7);
        RServo1.scaleRange(-1, 1);
        RServo2.scaleRange(-1, 1);
        BallHinge.scaleRange(-1 ,1);


        // Set all motors to zero power
        LDrive1.setPower(0);
        LDrive2.setPower(0);
        RDrive1.setPower(0);
        RDrive2.setPower(0);
        Intake.setPower(0);
        Shooter2.setPower(0);
        Lift.setPower(0);
        
        //Shooter2.setPower(0);

        //rightMotor.setPower(0);
        //armMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        LDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Shooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);/
        //armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        //leftClaw = hwMap.servo.get("left_hand");
        //rightClaw = hwMap.servo.get("right_hand");
        //leftClaw.setPosition(MID_SERVO);
        //rightClaw.setPosition(MID_SERVO);
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

