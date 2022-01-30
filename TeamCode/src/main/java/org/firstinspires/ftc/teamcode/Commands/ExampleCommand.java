package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class ExampleCommand extends SequentialCommandGroup {
    public ExampleCommand(Drivetrain drivetrain){

        Pose2d startPose = new Pose2d(0,0, Math.toRadians(90));

        drivetrain.setPoseEstimate(startPose);

        Trajectory traj1 = drivetrain.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(20, 20), Math.toRadians(0))
                .build();

        Trajectory traj2 = drivetrain.trajectoryBuilder(startPose, true)
                .splineTo(new Vector2d(20, 20), Math.toRadians(90))
                .build();
        addCommands(
                new TrajectoryFollowerCommand(drivetrain, traj1),
                new TrajectoryFollowerCommand(drivetrain, traj2)
        );
    }
}
