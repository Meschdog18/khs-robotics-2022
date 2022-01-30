package org.firstinspires.ftc.teamcode.Subsystems;

public interface Subsystem {
    default void initialize(){}
    default void periodic(){}
}
