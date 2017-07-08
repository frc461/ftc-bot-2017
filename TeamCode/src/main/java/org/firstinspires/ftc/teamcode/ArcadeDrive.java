package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.wbi.beck.ftc.controls.GamepadClass;
import org.wbi.beck.ftc.controls.mainHwMap;
import org.wbi.beck.ftc.controls.motorController;
import org.wbi.beck.ftc.logic.GlobalFunctions;

@TeleOp(name="ArcadeDrive", group="New")
public class ArcadeDrive extends OpMode{

    mainHwMap physicalRobot = new mainHwMap();
    motorController motorController = new motorController(physicalRobot);
    GamepadClass gamepadClass = new GamepadClass();
    GlobalFunctions gFunctions = new GlobalFunctions(motorController, gamepadClass);
    boolean intakePressed;
    @Override
    public void loop(){
        if(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.RIGHT) > 0){
            motorController.rSetPower(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.RIGHT));
            motorController.lSetPower(0);
            motorController.setLDirection(DcMotor.Direction.FORWARD);
            motorController.setRDirections(DcMotorSimple.Direction.FORWARD);

        } else if(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.RIGHT) < 0){
            motorController.lSetPower(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.RIGHT));
            motorController.rSetPower(0);
            motorController.setLDirection(DcMotor.Direction.REVERSE);
            motorController.setRDirections(DcMotorSimple.Direction.FORWARD);

        }else {
            motorController.lSetPower(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.LEFT));
            motorController.rSetPower(gamepadClass.getArcadeValues(gamepad1, GamepadClass.GenericSides.LEFT));
            motorController.setLDirection(DcMotorSimple.Direction.FORWARD);
            motorController.setRDirections(DcMotorSimple.Direction.FORWARD);

        }
        gFunctions.defaultIntake(gamepad2, gFunctions);
        gFunctions.defaultLift(gamepad2);
    }

    @Override
    public void init(){
        physicalRobot.init(hardwareMap);
    }

    @Override
    public void init_loop(){

    }

    public void start(){

    }

    @Override
    public void stop(){

    }
}
