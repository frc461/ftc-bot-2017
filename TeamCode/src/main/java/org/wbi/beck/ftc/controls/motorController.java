package org.wbi.beck.ftc.controls;


import com.qualcomm.robotcore.hardware.DcMotor;

public class motorController {

    mainHwMap hardware = null;

    public motorController(mainHwMap hardware){
        this.hardware = hardware;
    }

    public void rSetPower(double power){
        double p = speedSafety(hardware.RDrive1.getPower(), power);
        hardware.RDrive1.setPower(-1 * p);
        hardware.RDrive2.setPower(p);
    }

    public void lSetPower(double power){
        double p = speedSafety(hardware.LDrive1.getPower(), power);
        hardware.LDrive1.setPower(p);
        hardware.LDrive2.setPower(-1 * p);
    }

    public void setLDirection(DcMotor.Direction d){
        hardware.LDrive1.setDirection(d);
        hardware.LDrive2.setDirection(d);
    }

    public void setRDirections(DcMotor.Direction d){
        hardware.RDrive1.setDirection(d);
        hardware.RDrive2.setDirection(d);
    }

    public void setLMode(DcMotor.RunMode m){
        hardware.LDrive1.setMode(m);
        hardware.LDrive2.setMode(m);
    }

    public void setRMode(DcMotor.RunMode m){
        hardware.RDrive1.setMode(m);
        hardware.RDrive2.setMode(m);
    }

    public void batchSetMode(DcMotor.RunMode m){
        setLMode(m);
        setRMode(m);
    }

    public void setRTarget(int t){
        hardware.RDrive1.setTargetPosition(t);
        hardware.RDrive2.setTargetPosition(t);
    }

    public void setLTarget(int t){
        hardware.LDrive1.setTargetPosition(t);
        hardware.LDrive2.setTargetPosition(t);
    }

    public void batchSetTarget(int t){
        setRTarget(t);
        setLTarget(t);
    }

    public void shooter(double speed){
        hardware.Shooter.setPower(speed);
    }

    public double getShooterPosition(){
        return hardware.Shooter.getCurrentPosition();
    }

    public void controlIntakeMech(double speed){
        hardware.Intake.setPower(speed);
    }

    public double speedSafety(double current, double goal){
        double action = 0;
        if(current > 0 && goal == 0){
            for(double working_down = current; working_down > 0; working_down--){
                action = working_down;
            }
        }else if(current == 0 && goal > 0){
            for(double working_up = current; working_up < goal; working_up++){
                action =  working_up;

            }
        }else if(goal < 0){
            action = goal;
        }
        else{
            action = goal;
        }
        return action;
    }

    public void lift(double p){
        hardware.Lift.setPower(p);
    }


}
