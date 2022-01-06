package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.RobotCore.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;


public class Drive extends SubsystemBase{
    
    private RobotHardware robotHw;


    private HashMap<String, DcMotorEx> motors;
    private double maxMotorPower = .5;
    private final String[] motorNames = {
         "RearLeft",
         "RearRight",
         "FrontLeft",
         "FrontRight",
            "Intake",
            "Spinner"
    };

    public Drive(RobotHardware robotHw){
        this.robotHw = robotHw;

        motors = new HashMap<String, DcMotorEx>();

    }

    @Override
    public void initialize() {
        for(String name : motorNames){
            DcMotorEx motor = robotHw.getMotor(name);

            //motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            if(name.contains("Front")){
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            motor.setPower(0);

            motors.put(name, motor);

        }
        setMotorPower("FrontLeft", 0);
        setMotorPower("FrontRight", 0);
        setMotorPower("RearLeft", 0);
        setMotorPower("RearRight", 0);
        setMotorPower("Intake", 0);
        setMotorPower("Spinner", 0);


    }

    @Override
    public void periodic(){
        setMotorPower("Intake", 0);
        setMotorPower("Spinner", 0);
    }
    public int test(){return motors.size();}
    public void setMotorPower(String name, double power){
        motors.get(name).setPower(power);
    }
    public void updateMaxMotorSpeed(double power){this.maxMotorPower=power;}
    //takes joystick movements, and converts to motor commands
    //should prob be a command and not part of a subsystem
    public void transformJoystickDrive(Gamepad gamepad){
        double joy1Y = gamepad.left_stick_x;
        joy1Y = Math.abs(joy1Y) > 0.15 ? joy1Y*3/4: 0;
        double joy1X = gamepad.left_stick_y;
        joy1X = Math.abs(joy1X) > 0.15 ? joy1X*3/4: 0;
        double joy2X = gamepad.right_stick_x;
        joy2X = Math.abs(joy2X) > 0.15 ? joy2X*3/4: 0;
/*
        if(gamepad.a){
            setMotorPower("FrontLeft", -1);
        }
        if(gamepad.b){
            setMotorPower("FrontRight", 1);
        }
        if(gamepad.x){
            setMotorPower("RearLeft", 1);
        }
        if(gamepad.y){
            setMotorPower("RearRight", -1);
        }*/
        setMotorPower("FrontLeft", Math.max(-maxMotorPower, Math.min(maxMotorPower, joy1Y + joy2X - joy1X)));
        setMotorPower("FrontRight", Math.max(-maxMotorPower, Math.min(maxMotorPower, joy1Y + joy2X + joy1X)));
        setMotorPower("RearLeft", Math.max(-maxMotorPower, Math.min(maxMotorPower, joy1Y - joy2X + joy1X)));
        setMotorPower("RearRight", Math.max(-maxMotorPower, Math.min(maxMotorPower, joy1Y - joy2X - joy1X)));
    }

    
}
