package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.MecDrive;

public class FreightArm extends SubsystemBase{

        public static double UP_SPEED = 1;
        public static double DOWN_SPEED = -1;

        public static int SERVO_LOW = 0;
        public static int SERVO_HIGH = 1;

        private final DcMotorEx arm;
        private final Servo boxServo, armServo;

        private boolean holdArm = true;

        private int armServoPosition, boxServoPosition;

        public FreightArm(DcMotorEx armMotor,Servo boxServo, Servo armServo){
                arm = armMotor;
                this.boxServo = boxServo;
                this.armServo = armServo;
                armServoPosition = SERVO_LOW;
                boxServoPosition = SERVO_LOW;
                armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        @Override
        public void initialize() {
                arm.setTargetPosition(-1000);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                set(.25);
        }

        @Override
        public void periodic() {
                if(holdArm){
                        int armPos = arm.getCurrentPosition();
                        if(armPos < -980 && armPos > -1020){
                                arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                                set(0);
                                holdArm = false;
                        }
                }
        }


        //-5000
        public void set(double power){
                arm.setPower(power);
        }

        public void boomUp(){
                set(UP_SPEED);
        }

        public void boomDown(){
                if(arm.getCurrentPosition() > -5000){
                        set(DOWN_SPEED);
                }
        }

        public void boxServoFlip(){
                boxServoPosition = boxServoPosition == SERVO_LOW ? SERVO_HIGH : SERVO_LOW;
                boxServo.setPosition(boxServoPosition);
        }

        public void armServoFlip(){
                armServoPosition = armServoPosition == SERVO_LOW ? SERVO_HIGH : SERVO_LOW;
                armServo.setPosition(armServoPosition);
        }

        public void lowerArm(){

        }

        public void stop(){
                set(0);
        }
}
