package org.wbi.beck.ftc.logic;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.wbi.beck.ftc.controls.GamepadClass;
import org.wbi.beck.ftc.controls.motorController;

public class GlobalFunctions {
    public double last_shooter_reading = 0.0;
    motorController m_controller = null;
    private double shooter_diff = 0.0;
    private GamepadClass gamepadClass = null;

    public GlobalFunctions(motorController m, GamepadClass g){
        this.m_controller = m;
        this.gamepadClass = g;
    }

    public GlobalFunctions(motorController m){
        this.m_controller = m;
    }


    public void setShooterReading() {
        if (m_controller.getShooterPosition() > 0) {
            last_shooter_reading = m_controller.getShooterPosition();
        }
    }

    public void intake(){
        m_controller.controlIntakeMech(-0.75);
        m_controller.shooter(-1);
    }

    public void intake(double intakeSpeed, double shooterSpeed){
        m_controller.controlIntakeMech(intakeSpeed);
        m_controller.shooter(shooterSpeed);
    }
    public void defaultIntake(Gamepad gamepad2, GlobalFunctions gFunctions){
        if(gamepadClass.isIntakePressed(gamepad2)) {
            gFunctions.intake();
        } else{
            gFunctions.intake(gamepadClass.altIntakeSpeed(gamepad2), gamepadClass.altShooterSpeed(gamepad2));
        }
    }

    public void defaultLift(Gamepad g){
        m_controller.lift(gamepadClass.getLiftSpeed(g));
    }



}