package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;

public class CommandScheduler {
    private static CommandScheduler instance = null;

    //registered subsystems and their default commands
    private Map<Subsystem, Command> subsystems = new LinkedHashMap<>();

    //commands and their required subsystems
    private Map<Subsystem, Command> requirements = new LinkedHashMap<>();

    private Map<Command, CommandState> scheduledCommands = new LinkedHashMap<>();

    public static CommandScheduler getInstance(){
        if(instance == null){
            instance = new CommandScheduler();
        }
        return instance;
    }
    private void initializeCommand(Command command,boolean interruptible ,Set<Subsystem> requirements){
        command.initialize();
        CommandState state = new CommandState(interruptible);
        scheduledCommands.put(command, state);
        for(Subsystem requirement : requirements){
            this.requirements.put(requirement, command);
        }
    }
    private void schedule(boolean interruptible, Command command){

        Set<Subsystem> requirements = command.getRequirements();

        //if command's subsystem req
        if(Collections.disjoint(this.requirements.keySet(), requirements)){
            initializeCommand(command, interruptible ,requirements);
        }else{
            //interept command


            for(Subsystem requirement: requirements){
                Command currentCommand = this.requirements.get(requirement);
                if(this.requirements.containsKey(requirement)){

                    if(!scheduledCommands.get(currentCommand).isInterruptible()){
                        return; //if current command isn't interuptable, can't schedule new command
                    }
                    else{
                        // implement cancel
                    }

                }
            }
            
            initializeCommand(command, interruptible, requirements);
        }
    }

    public void schedule(boolean interruptible, Command... commands){
        for(Command command : commands){
            schedule(interruptible,command);
        }
    }

    public void schedule(Command... commands){
        schedule(true, commands);
    }

    public void run(){
        for(Subsystem subsystem : subsystems.keySet()){
            subsystem.periodic();
        }

        for(Command command : scheduledCommands.keySet()){
            command.execute();

            if(command.isFinished()){
                command.end(false);

                requirements.keySet().removeAll(command.getRequirements());
            }
        }
    }

    public void registerSubsystem(Subsystem... subsystems){
        for(Subsystem subsystem : subsystems){
            this.subsystems.put(subsystem, null);
        }
    }

    public void unregisterSubsystem(Subsystem... subsystems){
        this.subsystems.keySet().removeAll(Arrays.asList(subsystems));
    }

}

//https://github.com/FTCLib/FTCLib/blob/9250bf545a79e7dfe7308bf40085f1cac3ec71cc/core/src/main/java/com/arcrobotics/ftclib/command/CommandScheduler.java#L44