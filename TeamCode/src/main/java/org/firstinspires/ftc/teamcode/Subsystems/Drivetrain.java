package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.MecDrive;

public class Drivetrain extends SubsystemBase{
    private final MecDrive drive;
    private final boolean fieldCentric;

    public Drivetrain(HardwareMap hw, boolean isFieldCentric){
        this.drive = new MecDrive(hw);
        fieldCentric = isFieldCentric;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void periodic() {
    }

    public void drive(double leftY, double leftX, double rightX) {
        Pose2d poseEstimate = getPoseEstimate();

        Vector2d input = new Vector2d(-leftY, -leftX).rotated(
                fieldCentric ? -poseEstimate.getHeading() : 0
        );

        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        rightX
                )
        );
    }
    public void update(){
        drive.update();
    }
    public Pose2d getPoseEstimate() {
        return drive.getPoseEstimate();
    }
    public void setPoseEstimate(Pose2d pose){drive.setPoseEstimate(pose);}

    public void updateMaxDrivePower(double power){drive.setMaxPower(power);}
    public void setDrivePower(Pose2d drivePower){
        drive.setDrivePower(drivePower);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose){
        return drive.trajectoryBuilder(startPose);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed){
        return drive.trajectoryBuilder(startPose, reversed);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading){
        return drive.trajectoryBuilder(startPose, startHeading);
    }

    public void followTrajectory(Trajectory trajectory) {
        drive.followTrajectoryAsync(trajectory);
    }

    public boolean isBusy(){
        return drive.isBusy();
    }

    public void turn(double radians){
        drive.turnAsync(radians);
    }

    public void stop(){
        drive(0, 0, 0);
    }

    public Pose2d getPoseVelocity(){
        return drive.getPoseVelocity();
    }
}


