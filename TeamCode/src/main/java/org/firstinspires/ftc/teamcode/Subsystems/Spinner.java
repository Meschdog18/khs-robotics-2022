package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Spinner extends SubsystemBase{
    public static double SPIN_SPEED = -1;
    public static double REVERSE_SPIN_SPEED = 1;

    private final DcMotorEx spinner;

    public Spinner(DcMotorEx spinnerMotor){
        spinner = spinnerMotor;
    }

    @Override
    public void initialize() {
        set(0);
    }

    @Override
    public void periodic() {
    }

    public void set(double power){
        spinner.setPower(power);
    }

    public void spin(){
        set(SPIN_SPEED);
    }

    public void reverseSpin(){
        set(REVERSE_SPIN_SPEED);
    }

    public void stop(){
        set(0);
    }
}
