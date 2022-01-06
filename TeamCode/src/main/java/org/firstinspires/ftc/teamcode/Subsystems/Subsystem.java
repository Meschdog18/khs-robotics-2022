package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Commands.CommandScheduler;

public interface Subsystem {
    default void initialize(){}
    default void periodic() {}

}
