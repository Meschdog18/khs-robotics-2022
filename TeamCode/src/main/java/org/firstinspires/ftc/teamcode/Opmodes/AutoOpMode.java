package org.firstinspires.ftc.teamcode.Opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.ExampleCommand;
import org.firstinspires.ftc.teamcode.Commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.FreightArm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;

//add initizalzed boolean to subsytems, so subsystems like vision can be polled in init stage and return data to driver when fully ready
//add debug mode, that tells all subsytems to return telemetry packets
@Autonomous(name= "Comp auto")
public class AutoOpMode extends MatchOpMode {
    private DcMotorEx armMotor, spinnerMotor, intakeMotor;
    private Servo armServo, boxServo;


    private Drivetrain drivetrain;
    private Intake intake;
    private Vision vision;
    private FreightArm freightArm;

    @Override
    public void initialize() {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        armMotor = hardwareMap.get(DcMotorEx.class, "freightArm");
        armServo = hardwareMap.get(Servo.class, "freightArmServo");
        boxServo = hardwareMap.get(Servo.class, "freightBoxServo");
        spinnerMotor = hardwareMap.get(DcMotorEx.class, "spinner");

        drivetrain = new Drivetrain(hardwareMap, false);
        intake = new Intake(intakeMotor);
        freightArm = new FreightArm(armMotor, boxServo, armServo);
        //vision = new Vision(hardwareMap, "Webcam 1", telemetry);
        robot.register(drivetrain, intake, freightArm);
    }

    @Override
    public void matchStart() {
        //vision.getCurrentLocation();
        Trajectory traj = drivetrain.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(36, 36), Math.toRadians(0))
                .build();

        robot.schedule(new TrajectoryFollowerCommand(drivetrain, traj));

        //robot.schedule(new ExampleCommand(drivetrain));
        //robot.schedule(new MecDrive(drivetrain, gamepad1));
    }
}
