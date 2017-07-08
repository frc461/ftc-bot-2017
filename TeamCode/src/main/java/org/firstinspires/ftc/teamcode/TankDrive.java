package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.wbi.beck.ftc.controls.GamepadClass;
import org.wbi.beck.ftc.controls.mainHwMap;
import org.wbi.beck.ftc.controls.motorController;
import org.wbi.beck.ftc.logic.GlobalFunctions;

@TeleOp(name="TankDrive", group="New")
public class TankDrive extends OpMode{

    mainHwMap physicalRobot = new mainHwMap();
    motorController motorController = new motorController(physicalRobot);
    GamepadClass gamepadClass = new GamepadClass();
    GlobalFunctions gFunctions = new GlobalFunctions(motorController, gamepadClass);
    int start_pos;

    @Override
    public void loop(){
        motorController.lSetPower(gamepadClass.getTankValues(gamepad1, GamepadClass.GenericSides.LEFT));
        motorController.rSetPower(gamepadClass.getTankValues(gamepad1, GamepadClass.GenericSides.RIGHT));
        gFunctions.defaultIntake(gamepad2, gFunctions);
        gFunctions.defaultLift(gamepad2);

        telemetry.addData("RDrive1", physicalRobot.RDrive1.getCurrentPosition() - start_pos);
        telemetry.addData("LDrive1", physicalRobot.LDrive1.getCurrentPosition() - start_pos);
    }

    @Override
    public void init(){
        physicalRobot.init(hardwareMap);
    }

    @Override
    public void init_loop(){

    }

    public void start(){
        start_pos = physicalRobot.RDrive1.getCurrentPosition();
    }

    @Override
    public void stop(){

    }
}
