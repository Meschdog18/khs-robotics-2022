package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class ElementLifter extends SubsystemBase{
    public final double ELEMENT_SERVO_LOW = 0;
    public final double ELEMENT_SERVO_HIGH = .5;

    public final double ELEMENT_CLAW_SERVO_LOW = 0;
    public final double ELEMENT_CLAW_SERVO_HIGH = 1;

    public static double UP_SPEED = 1;
    public static double DOWN_SPEED = -1;

    private final DcMotorEx lifter;
    private final Servo lifterServo, lifterClawServo;

    private double elementServoPosition, elementClawServoPosition;

    public ElementLifter(DcMotorEx lifterMotor, Servo lifterServo, Servo lifterClawServo){
        lifter = lifterMotor;
        this.lifterServo = lifterServo;
        this.lifterClawServo = lifterClawServo;
        lifterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void initialize() {
        set(0);
    }

    @Override
    public void periodic() {
    }
    public void set(double power){
        lifter.setPower(power);
    }

    public void boomUp(){
        set(UP_SPEED);
    }

    public void boomDown(){
        set(DOWN_SPEED);
    }

    public void elementServoFlip(){
        elementServoPosition = elementServoPosition == ELEMENT_SERVO_LOW ? ELEMENT_SERVO_HIGH : ELEMENT_SERVO_LOW;
        lifterServo.setPosition(elementServoPosition);
    }

    public void elementClawServoFlip(){
        elementClawServoPosition = elementClawServoPosition == ELEMENT_CLAW_SERVO_LOW ? ELEMENT_CLAW_SERVO_HIGH : ELEMENT_CLAW_SERVO_LOW;
        lifterClawServo.setPosition(elementClawServoPosition);
    }

    public void stop(){
        set(0);
    }
}
