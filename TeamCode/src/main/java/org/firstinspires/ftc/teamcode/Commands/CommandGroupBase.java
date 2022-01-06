package org.firstinspires.ftc.teamcode.Commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public abstract class CommandGroupBase extends CommandBase implements Command{
    private static final Set<Command> commands = Collections.newSetFromMap(new WeakHashMap<>());

    static void registerCommands(Command... commands){
        CommandGroupBase.commands.addAll(Arrays.asList(commands));
    }

    public static void clearCommands(){
        commands.clear();
    }

    public static void clearCommand(Command command){
        commands.remove(command);
    }

    static Set<Command> getCommands(){
        return commands;
    }

    public static void requireUnregistered(Command... commands){
        requireUnregistered(Arrays.asList(commands));
    }

    public static void requireUnregistered(Collection<Command> commands){
        if(!Collections.disjoint(commands, getCommands())){
            throw new IllegalArgumentException("Commands can only be added to one command group");
        }
    }

    public abstract void addCommands(Command... commands);
}
