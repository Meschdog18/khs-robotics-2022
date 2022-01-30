package org.firstinspires.ftc.teamcode.Commands.CommandLib;

public class CommandState{

    private final boolean interruptible;
    public CommandState(boolean interruptible){
        this.interruptible = interruptible;
    }

    boolean isInterruptible(){
        return interruptible;
    }
    // add interuptable state, plus look at frc implementation
}