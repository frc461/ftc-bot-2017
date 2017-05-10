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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Posty Thingy Blue")
//@Disabled



public class ToPostyThingyBlue extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor RDrive1;
    DcMotor RDrive2;
    DcMotor LDrive1;
    DcMotor LDrive2;
    DcMotor Shooter1;
    DcMotor Intake;
    boolean devMode = true;
    String action;

    public void moveBetter(double speed, int target){
        if(devMode == true){
            RDrive1.setPower(0.5);
            RDrive2.setPower(0.5);
            LDrive1.setPower(0.5);
            LDrive2.setPower(0.5);
        } else{
            RDrive1.setPower(speed);
            RDrive2.setPower(speed);
            LDrive1.setPower(speed);
            LDrive2.setPower(speed);
        }


        RDrive1.setTargetPosition(RDrive1.getCurrentPosition() + target);
        LDrive1.setTargetPosition(LDrive1.getCurrentPosition() + target);
        RDrive2.setTargetPosition(RDrive2.getCurrentPosition() + target);
        LDrive2.setTargetPosition(LDrive2.getCurrentPosition() + target);

        while(RDrive1.getCurrentPosition() < target) {}
    }
    public void Shooter(int p){
        //Shooter2.setPower(p);
        Shooter1.setPower(p);
    }
    public void slep(int s){
        sleep(s);
    }

    @Override
    public void runOpMode() throws InterruptedException{

        //Find them motors
        RDrive1 = hardwareMap.dcMotor.get("R Drive 1");
        RDrive2 = hardwareMap.dcMotor.get("R Drive 2");
        LDrive1 = hardwareMap.dcMotor.get("L Drive 1");
        LDrive2 = hardwareMap.dcMotor.get("L Drive 2");
        Shooter1 = hardwareMap.dcMotor.get("Shooter 2");
        //Shooter2 = hardwareMap.dcMotor.get("Shooter 2");
        Intake = hardwareMap.dcMotor.get("Intake");

        LDrive1.setDirection(DcMotor.Direction.REVERSE);
        LDrive2.setDirection(DcMotor.Direction.FORWARD);
        RDrive1.setDirection(DcMotor.Direction.FORWARD);
        RDrive2.setDirection(DcMotor.Direction.REVERSE);
        Shooter1.setDirection(DcMotor.Direction.FORWARD);
        //Shooter2.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.REVERSE);

        int MStart;
        int MTarget;

        MTarget = 1920;

        MStart = RDrive1.getCurrentPosition();//Stores in a variable what the position the motors start in

        RDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Play when you play
        waitForStart();

        moveBetter(0.5, 400);
        action = "move to a better place, ";
        Shooter(1);
        action = "Start shooters";
        slep(1000);
        Intake.setPower(1);
        action = "Start Intake";
        slep(5000);
        Intake.setPower(0);
        action = "Turn off Intake and shooter";
        Shooter(0);
        moveBetter(0.5, 1930);
        action = "Knock off ball";


        while (opModeIsActive() && (runtime.seconds() < 15.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("Target Position", RDrive1.getTargetPosition());
            telemetry.addData("Current Positon", RDrive1.getCurrentPosition());
            telemetry.addData("Current Action", action);
            telemetry.addData("RDrive1", RDrive1.getCurrentPosition());
            telemetry.addData("LDrive1", LDrive1.getCurrentPosition());
            telemetry.update();
        }


    }
}
