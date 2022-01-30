package org.firstinspires.ftc.teamcode.Commands.CommandLib;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;
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

    private final Collection<Runnable> buttons = new LinkedHashSet<>();


    public static CommandScheduler getInstance(){
        if(instance == null){
            instance = new CommandScheduler();
        }
        return instance;
    }

    public void reset(){
        instance = null;
    }

    public void addButton(Runnable button) {
        buttons.add(button);
    }

    public void clearButtons() {
        buttons.clear();
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

        if(Collections.disjoint(this.requirements.keySet(), requirements)){
            initializeCommand(command, interruptible ,requirements);
        }else{
            for(Subsystem requirement: requirements){
                Command currentCommand = this.requirements.get(requirement);
                if(this.requirements.containsKey(requirement)){

                    if(!scheduledCommands.get(currentCommand).isInterruptible()){
                        return; //if current command isn't interuptable, can't schedule new command
                    }
                    else{
                        cancel((currentCommand));
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

    public void cancel(Command... commands){
        for(Command command : commands){
            if(!scheduledCommands.containsKey((command))){continue;} // if command not currently scheduled, skip

            command.end(true);
            scheduledCommands.remove(command);
            requirements.keySet().removeAll(command.getRequirements()); //frees all subsystems command depended on
        }
    }

    public void cancelAll(){
        for(Command command : scheduledCommands.keySet()){
            cancel(command);
        }
    }

    public void run(){
        for(Subsystem subsystem : subsystems.keySet()){
            subsystem.periodic();
        }

        for (Runnable button : buttons) {
            button.run();
        }

        for(Command command : scheduledCommands.keySet()){
            command.execute();


            if(command.isFinished()){
                command.end(false);


                requirements.keySet().removeAll(command.getRequirements());
                scheduledCommands.remove(command);
            }
        }


    }

    public void registerSubsystem(Subsystem... subsystems){
        for(Subsystem subsystem : subsystems){
            this.subsystems.put(subsystem, null);
        }
    }
    public void initalizeSubystems(){
        for(Subsystem subsystem : subsystems.keySet()){
            subsystem.initialize();
        }
    }
    public void unregisterSubsystem(Subsystem... subsystems){
        this.subsystems.keySet().removeAll(Arrays.asList(subsystems));
    }

    public boolean isScheduled(Command... commands) {
        return scheduledCommands.keySet().containsAll(Arrays.asList(commands));
    }
}

//https://github.com/FTCLib/FTCLib/blob/9250bf545a79e7dfe7308bf40085f1cac3ec71cc/core/src/main/java/com/arcrobotics/ftclib/command/CommandScheduler.java#L44