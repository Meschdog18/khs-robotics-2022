package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

public class TrajectoryFollowerCommand extends CommandBase {

    private final Drivetrain drive;
    private final Trajectory trajectory;

    public TrajectoryFollowerCommand(Drivetrain drive, Trajectory trajectory){
        this.drive = drive;
        this.trajectory = trajectory;

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.followTrajectory(trajectory);
    }

    @Override
    public void execute() {
        drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if(interrupted){
            drive.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return !drive.isBusy();
    }
}
