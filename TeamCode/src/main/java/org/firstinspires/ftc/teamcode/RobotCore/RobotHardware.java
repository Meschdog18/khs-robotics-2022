package org.firstinspires.ftc.teamcode.RobotCore;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.CommandScheduler;

import java.util.List;

public class RobotHardware {

    private static RobotHardware instance = null;
    private static HardwareMap hwMap = null;

    private List<LynxModule> lynxHubs;

    public static RobotHardware getInstance(){
        if(instance == null){
            instance = new RobotHardware();
        }
        return instance;
    }

    public RobotHardware(){

    }
    public void setHardwareMap(HardwareMap hwMap){
        this.hwMap = hwMap;

        this.lynxHubs = hwMap.getAll(LynxModule.class);

        for(LynxModule hub : lynxHubs){
            //may cause problems if same hardware is read twice in same loop
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }

    public DcMotorEx getMotor(String motorName){
        return hwMap.get(DcMotorEx.class, motorName);
    }

}
//can interface with hardware thro RobotHardware, but actually init individual hardware in subsystem