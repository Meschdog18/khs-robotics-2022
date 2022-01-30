package org.firstinspires.ftc.teamcode.Commands.CommandLib;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import java.util.Set;

public interface Command {
    default void initialize(){};
    default void execute(){};
    default void end(boolean interrupted){};
    default boolean isFinished(){return false;};
    Set<Subsystem> getRequirements();
    default void schedule(boolean interruptible) {
        CommandScheduler.getInstance().schedule(interruptible, this);
    }

    /**
     * Schedules this command, defaulting to interruptible.
     */
    default void schedule() {
        schedule(true);
    }

    /**
     * Cancels this command.  Will call the command's interrupted() method.
     * Commands will be canceled even if they are not marked as interruptible.
     */
    default void cancel() {
        CommandScheduler.getInstance().cancel(this);
    }

    default boolean isScheduled() {
        return CommandScheduler.getInstance().isScheduled(this);
    }
}
