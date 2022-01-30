package org.firstinspires.ftc.teamcode.RobotCore;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.Command;
import org.firstinspires.ftc.teamcode.Commands.CommandLib.CommandScheduler;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class Robot {
    public static boolean isDisabled = false; //if disabled shuts down all robot processes

    public void run(){
        CommandScheduler.getInstance().run();
    }

    public void schedule(Command... commands){
        CommandScheduler.getInstance().schedule(commands);
    }
    public void init(){
        CommandScheduler.getInstance().initalizeSubystems();
    }
    public void register(Subsystem... subsystems){
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    public void reset(){
        CommandScheduler.getInstance().reset();
    }

    public CommandScheduler getCommandScheduler(){
        return CommandScheduler.getInstance();
    }
    public static void disable(){
        isDisabled = true;
    }

    public static void enable(){
        isDisabled = false;
    }

}


