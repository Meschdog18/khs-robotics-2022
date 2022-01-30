package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends SubsystemBase{
    public static double INTAKE_SPEED = -1;
    public static double OUTAKE_SPEED = 1;

    private final DcMotorEx intake;

    public Intake(DcMotorEx intakeMotor){
        intake = intakeMotor;
    }

    @Override
    public void initialize() {
        set(0);
    }

    @Override
    public void periodic() {
    }

    public void set(double power){
        intake.setPower(power);
    }

    public void intake(){
        set(INTAKE_SPEED);
    }

    public void outtake(){
        set(OUTAKE_SPEED);
    }

    public void stop(){
        set(0);
    }
}
