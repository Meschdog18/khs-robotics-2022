package org.firstinspires.ftc.teamcode.Commands.CommandLib;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class InstantCommand extends CommandBase{
    //function to run
    private final Runnable toRun;

    public InstantCommand(Runnable toRun, Subsystem... requirements){
        this.toRun = toRun;
        addRequirements(requirements);
    }

    @Override
    public void initialize(){
        toRun.run();
    }

    @Override
    public final boolean isFinished(){
        return true;
    }
}
