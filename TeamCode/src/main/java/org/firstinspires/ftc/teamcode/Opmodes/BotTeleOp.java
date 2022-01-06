package org.firstinspires.ftc.teamcode.Opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.RobotCore.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.Commands.MecDrive;
import org.firstinspires.ftc.teamcode.Commands.CommandScheduler;
@TeleOp(name="test1", group="Linear Opmode")
public class BotTeleOp extends LinearOpMode {

    private Robot robot;

    @Override
    public void runOpMode(){
        Robot robot = new Robot();

        waitForStart();


        while(opModeIsActive()){
            MecanumDriveSubsystem drive = new MecanumDriveSubsystem(hardwareMap, false);
            Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                    .strafeRight(10)
                    .build();

            TrajectoryFollowerCommand command = new TrajectoryFollowerCommand(drive, traj1);
            robot.schedule(command);

            robot.run();
        }

    }
}
