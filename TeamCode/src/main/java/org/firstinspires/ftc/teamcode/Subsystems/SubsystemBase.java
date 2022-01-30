package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.CommandScheduler;

public class SubsystemBase implements Subsystem{
    public SubsystemBase(){
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    //add getTelemetryPacket function
}
