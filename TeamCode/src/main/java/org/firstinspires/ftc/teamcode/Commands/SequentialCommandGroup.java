package org.firstinspires.ftc.teamcode.Commands;

import java.util.ArrayList;
import java.util.List;

public class SequentialCommandGroup extends CommandGroupBase {
    private final List<Command> commands = new ArrayList<>();
    private int commandIndex = -1; //index of current command being executed

    public SequentialCommandGroup(Command... commands){
        addCommands(commands);
    }

    @Override
    public void addCommands(Command... commands){
        requireUnregistered(commands);

        if(commandIndex != -1){
            throw new IllegalStateException("Commands cannot be added to a CommandGroup while group is running");
        }

        registerCommands(commands);

        for(Command command : commands){
            this.commands.add(command);
            requirements.addAll(command.getRequirements()); //stores all subsystem requirements; doesn't matter if some overlap because each command runs sequentially
        }
    }

    @Override
    public void initialize() {
        commandIndex = 0; //first command

        if(!commands.isEmpty()){
            commands.get(0).initialize();
        }

        //just starts off command group with first command in sequence
    }

    @Override
    public void execute() {
        if(commands.isEmpty()){
            return;
        }

        Command currentCommand = commands.get(commandIndex);
        currentCommand.execute();
        if(currentCommand.isFinished()){
            currentCommand.end(false);
            commandIndex++;

            //if any more commands in list, init the next one
            if(commandIndex < commands.size()){
                commands.get(commandIndex).initialize();
            }
        }
    }

    @Override
    public void end(boolean interrupted){
        if(interrupted && !commands.isEmpty()){
            commands.get(commandIndex).end(true); //if end request sent with interupt, and there's a next command, interupt that command
        }

        commandIndex = -1; //reset index
    }

    @Override
    public boolean isFinished(){
        return commandIndex == commands.size();
    }
}
