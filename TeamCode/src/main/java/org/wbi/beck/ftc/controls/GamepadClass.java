package org.wbi.beck.ftc.controls;

import com.qualcomm.robotcore.hardware.Gamepad;

//Intake a
//Bumpers for speed


public class GamepadClass {


    public boolean isIntakePressed(Gamepad g){
        return g.a;
    }

    public float liftValue(Gamepad g) { return g.left_stick_y; }

    public float getSpeed(Gamepad g){
        return g.right_trigger;
    }

    public double altIntakeSpeed(Gamepad g) { return g.left_stick_y;}

    public boolean altShooterEject(Gamepad g) { return g.dpad_down;}

    public boolean altShooterIntake(Gamepad g) { return g.dpad_up; }

    public double getLiftSpeed(Gamepad g) { return g.right_stick_y; }

    //Compatibility Function
    public double altShooterSpeed(Gamepad g){
        if(altShooterIntake(g)){
            return -1;
        } else if(altShooterEject(g)){
            return 1;
        }else{
            return 0;
        }
    }

    public enum Direction{
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public Direction getThrottleDirection(Gamepad g){
        if(g.right_stick_x > 0){
            return Direction.RIGHT;
        } else if(g.right_stick_x < 0){
            return  Direction.LEFT;
        } else if(g.right_stick_y < 0){
            return Direction.FORWARD;
        } else if(g.right_stick_y > 0){
            return Direction.BACKWARD;
        } else {
            return null;
        }

    }

    public enum GenericSides {
        LEFT,
        RIGHT
    }

    public double getTankValues(Gamepad g, GenericSides side){
        double value = 0;
        switch (side){
            case LEFT:
                value = g.left_stick_y;
                break;
            case RIGHT:
                value = g.right_stick_y;
                break;
        }
        return value;
    }

    public double getArcadeValues(Gamepad g, GenericSides side){
        double value = 0;
        switch (side){
            case LEFT:
                value = g.left_stick_y;
                break;
            case RIGHT:
                value = g.right_stick_x;
                break;
        }
        return value;
    }
}
