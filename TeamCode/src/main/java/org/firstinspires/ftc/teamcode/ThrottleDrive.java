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

import org.wbi.beck.ftc.controls.GamepadClass;
import org.wbi.beck.ftc.controls.mainHwMap;
import org.wbi.beck.ftc.controls.motorController;
import org.wbi.beck.ftc.logic.GlobalFunctions;

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

@TeleOp(name="ThrottleDrive", group="New")
//@Disabled
public class ThrottleDrive extends OpMode{
    mainHwMap physicalRobot = new mainHwMap();
    motorController motorController = new motorController(physicalRobot);
    GamepadClass gamepadClass = new GamepadClass();
    GlobalFunctions gFunctions = new GlobalFunctions(motorController, gamepadClass);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        physicalRobot.init(hardwareMap);
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

    }

    @Override
    public void loop() {
        double speed = gamepadClass.getSpeed(gamepad1);
        GamepadClass.Direction direction = gamepadClass.getThrottleDirection(gamepad1);

        if(direction == GamepadClass.Direction.FORWARD){
            motorController.lSetPower(-speed);
            motorController.rSetPower(-speed);
        } else if(direction == GamepadClass.Direction.BACKWARD){
            motorController.lSetPower(speed);
            motorController.rSetPower(speed);
        } else if(direction == GamepadClass.Direction.LEFT){
            motorController.rSetPower(-1 * speed);
            motorController.lSetPower(speed);
        } else if(direction == GamepadClass.Direction.RIGHT){
            motorController.lSetPower(speed);
            motorController.rSetPower(-1 * speed);
        }else{
            motorController.lSetPower(0);
            motorController.rSetPower(0);
        }

        gFunctions.defaultIntake(gamepad2, gFunctions);
        gFunctions.defaultLift(gamepad2);

        telemetry.addData("Direction", direction);
        telemetry.addData("Speed", speed);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
