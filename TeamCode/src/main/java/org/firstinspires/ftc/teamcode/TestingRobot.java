/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Remote", group="Testing")
//@Disabled
public class TestingRobot extends OpMode{
    int startPosition;
    /* Declare OpMode members. */
    HardwarePushbot robot       = new HardwarePushbot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo
    double          last_shooter_reading = 0.0;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Robot Testing ready");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        startPosition = robot.RDrive1.getCurrentPosition();
        robot.LServo1.setPosition(1.0);
        robot.LServo2.setPosition(1.0);
        robot.RServo1.setPosition(1.0);
        robot.RServo2.setPosition(1.0);
    }

    //Control Left Drive motors in one blow
    public void LDrive(Float p){
        robot.LDrive1.setPower(p);
        robot.LDrive2.setPower(p);
    }

    //Control Right Drive motors in one blow
    public void RDrive(Float p) {
        robot.RDrive2.setPower(p);
        robot.RDrive1.setPower(p);
    }

    //Control Shooter_old in one blow
    public void Shooter(double p){
        robot.Shooter1.setPower(p);
        //robot.Shooter2.setPower(p);
    }

    @Override
    public void loop() {



        //Define motor/server variables
        float left;
        float right;
        double intake;
        double shooter;
        double shooter_diff;
        String intakeStatus = "off";
        String shooterStatus = "off";
        String hoodServoStatus = "none";








        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        intake = gamepad2.left_stick_y;
        shooter = gamepad2.right_stick_y;


        //Moving Controls
        if(gamepad1.right_trigger > 0){ //If you hit the right trigger then it should move forward
            LDrive(gamepad1.right_trigger);
            RDrive(gamepad1.right_trigger);
            robot.LDrive1.setDirection(DcMotor.Direction.FORWARD);
            robot.LDrive2.setDirection(DcMotor.Direction.REVERSE);
            robot.RDrive1.setDirection(DcMotor.Direction.REVERSE);
            robot.RDrive2.setDirection(DcMotor.Direction.FORWARD);

        } else if(gamepad1.left_trigger > 0){ //If you hit the left trigger then it should move backwards
            LDrive(gamepad1.right_trigger);
            RDrive(gamepad1.right_trigger);
            robot.LDrive1.setDirection(DcMotor.Direction.REVERSE);
            robot.LDrive2.setDirection(DcMotor.Direction.FORWARD);
            robot.RDrive1.setDirection(DcMotor.Direction.FORWARD);
            robot.RDrive2.setDirection(DcMotor.Direction.REVERSE);
        }  else { //If you dont touch the triggers then use the sticks to move it manually
            LDrive(left);
            RDrive(right);
            robot.LDrive1.setDirection(DcMotor.Direction.FORWARD);
            robot.LDrive2.setDirection(DcMotor.Direction.REVERSE);
            robot.RDrive1.setDirection(DcMotor.Direction.REVERSE);
            robot.RDrive2.setDirection(DcMotor.Direction.FORWARD);
        }

        //Shooter_old Control
        if(gamepad2.b){
            Shooter(1);
        } else{
            Shooter(shooter);
            shooterStatus = "???";
        }

        //Intake Control

        if (gamepad2.b) {
            shooter_diff = robot.Shooter1.getCurrentPosition() - last_shooter_reading;
            telemetry.addData("Shooter_old Difference", shooter_diff);
            if(shooter_diff > -20){
                robot.Intake.setPower(-1);
                intakeStatus = "intaking, and shooting";
            }
        } else{
            robot.Intake.setPower(intake);
            intakeStatus = "???";
        }

        if (gamepad1.a){
            robot.LServo1.setPosition(1.0);
            robot.LServo2.setPosition(1.0);
            robot.RServo1.setPosition(1.0);
            robot.RServo2.setPosition(1.0);
        }
        if (gamepad1.b){
            robot.LServo1.setPosition(-1.0);
            robot.LServo2.setPosition(-1.0);
            robot.RServo1.setPosition(-1.0);
            robot.RServo2.setPosition(-1.0);
        }

        //Ball Stopper Controls
        if (gamepad2.x){
            robot.BallStop.setPosition(0.5);
            if(robot.Intake.getPower() == 0){
                robot.Intake.setPower(-1);
                if(robot.BallStop.getPosition() == 0.5) {
                    robot.Intake.setPower(0);
                }
            } else{
                robot.Intake.setPower(-1);
                if(robot.BallStop.getPosition() == 0.5) {
                    robot.Intake.setPower(1);
                }
            }
        }
        if (gamepad2.y){
            robot.BallStop.setPosition(0);
          if(robot.Intake.getPower() == 0){
            robot.Intake.setPower(-1);
              if(robot.BallStop.getPosition() == 0) {
                  robot.Intake.setPower(0);
              }
        } else {
              robot.Intake.setPower(-1);
              if(robot.BallStop.getPosition() == 0) {
                  robot.Intake.setPower(1);
              }
          }
        }

        //Ball Lift Controlls
        if (gamepad2.back){
            robot.BallHinge.setPosition(1);
        }else{
            robot.BallHinge.setPosition(-1);
        }


        //Lift Control
        if(gamepad2.dpad_up){
            robot.Lift.setPower(0.3);
        } else if(gamepad2.dpad_down){
            robot.Lift.setPower(-0.3);
        } else{
            robot.Lift.setPower(0);
        }

        //Set the last shooter position if not shooting
        if(robot.Shooter1.getPower() > 0){
            last_shooter_reading = robot.Shooter1.getCurrentPosition();
        }

        telemetry.addData("Intake State", intakeStatus);
        telemetry.addData("Shooter_old Status", shooterStatus);
        telemetry.addData("Shooter_old Speed", robot.Shooter1.getPower());
        telemetry.addData("Drive Position", robot.LDrive1.getCurrentPosition() - startPosition);
        telemetry.addData("L Servo 1", robot.LServo1.getPosition());
        telemetry.addData("L Server 2", robot.LServo2.getPosition());
        telemetry.addData("R Servo 1", robot.RServo1.getPosition());
        telemetry.addData("R Server 2", robot.RServo2.getPosition());

        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
