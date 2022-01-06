package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class CommandBase implements Command {
    protected Set<Subsystem> requirements = new HashSet<>();

    public final void addRequirements(Subsystem... requirements) {
        this.requirements.addAll(Arrays.asList(requirements));
    }
    @Override
    public Set<Subsystem> getRequirements(){
        return requirements;
    }

}
