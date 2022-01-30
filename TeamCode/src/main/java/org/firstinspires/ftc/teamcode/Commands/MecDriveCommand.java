package org.firstinspires.ftc.teamcode.Commands;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.CommandBase;
import org.firstinspires.ftc.teamcode.Gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class MecDriveCommand extends CommandBase {
    private Drivetrain driveSubsystem;
    private GamepadEx gamepad;

    public MecDriveCommand(Drivetrain subsystem, GamepadEx gamepad){
        driveSubsystem = subsystem;
        this.gamepad = gamepad;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(
                -gamepad.getLeftY(),
                gamepad.getLeftX(),
                -gamepad.getRightX()
        );
        /*
        if(gamepad.right_bumper){
            driveSubsystem.updateMaxDrivePower(1);
        }
        if(gamepad.left_bumper){
            driveSubsystem.updateMaxDrivePower(.5);
        }*/

    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
