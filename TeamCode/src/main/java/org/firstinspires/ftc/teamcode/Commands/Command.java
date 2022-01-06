package org.firstinspires.ftc.teamcode.Commands;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import java.util.Set;

public interface Command {
    default void initialize(){};
    default void execute(){};
    default void end(boolean interrupted){};
    default boolean isFinished(){return false;};
    Set<Subsystem> getRequirements();

}
