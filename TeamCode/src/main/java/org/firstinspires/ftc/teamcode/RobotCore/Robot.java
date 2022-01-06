package org.firstinspires.ftc.teamcode.RobotCore;

import org.firstinspires.ftc.teamcode.Commands.Command;
import org.firstinspires.ftc.teamcode.Commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class Robot {
    private CommandScheduler commandScheduler = CommandScheduler.getInstance();
    public static boolean isDisabled = false; //if disabled shuts down all robot processes

    public void run(){
        commandScheduler.run();
    }

    public void schedule(Command... commands){
        commandScheduler.schedule(commands);
    }

    public void register(Subsystem... subsystems){
        commandScheduler.registerSubsystem(subsystems);
    }

    public static void disable(){
        isDisabled = true;
    }

    public static void enable(){
        isDisabled = false;
    }

}


