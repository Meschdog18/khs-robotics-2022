package org.firstinspires.ftc.teamcode.Commands;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commands.Command;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class MecDrive extends CommandBase{
    private Drive driveSubsystem;
    private Gamepad gamepad;

    public MecDrive(Drive subsystem, Gamepad gamepad){
        driveSubsystem = subsystem;
        this.gamepad = gamepad;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.transformJoystickDrive(this.gamepad);
        if(gamepad.right_bumper){
            driveSubsystem.updateMaxMotorSpeed(1);
        }
        if(gamepad.left_bumper){
            driveSubsystem.updateMaxMotorSpeed(.5);
        }
        if(gamepad.dpad_down){
            driveSubsystem.setMotorPower("Intake", 1);
        }
        if(gamepad.dpad_up){
            driveSubsystem.setMotorPower("Intake", -1);
        }
        if(gamepad.a){
            driveSubsystem.setMotorPower("Spinner", 1);
        }

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
