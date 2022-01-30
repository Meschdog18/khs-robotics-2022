package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.CommandLib.button.Button;
import org.firstinspires.ftc.teamcode.Commands.CommandLib.button.GamepadButton;
import org.firstinspires.ftc.teamcode.Commands.MecDriveCommand;
import org.firstinspires.ftc.teamcode.Gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.FreightArm;
import org.firstinspires.ftc.teamcode.Subsystems.ElementLifter;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Spinner;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="comp teleop", group="Linear Opmode")
public class TeleOp extends MatchOpMode{
    private DcMotorEx armMotor, spinnerMotor, intakeMotor, lifterMotor;
    private Servo armServo, boxServo;
    private Servo elementLifterServo, elementLifterClawServo;

    private Drivetrain drivetrain;
    private Intake intake;
    private FreightArm freightArm;
    private ElementLifter elementLifter;
    private Spinner spinner;

    private GamepadEx driverGamepad;
    private GamepadEx operatorGamepad;

    private Button intakeButton, outtakeButton;
    private Button boomUpButton, boomDownButton;
    private Button armServoButton;
    private Button spinButton;
    @Override
    public void initialize() {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        armMotor = hardwareMap.get(DcMotorEx.class, "freightArm");
        armServo = hardwareMap.get(Servo.class, "freightArmServo");
        boxServo = hardwareMap.get(Servo.class, "freightBoxServo");
        elementLifterServo = hardwareMap.get(Servo.class, "elementLifterServo");
        elementLifterClawServo = hardwareMap.get(Servo.class, "elementLifterClawServo");
        spinnerMotor = hardwareMap.get(DcMotorEx.class, "spinner");
        lifterMotor = hardwareMap.get(DcMotorEx.class, "elementLifter");


        drivetrain = new Drivetrain(hardwareMap, false);
        intake = new Intake(intakeMotor);
        freightArm = new FreightArm(armMotor, boxServo, armServo);
        elementLifter = new ElementLifter(lifterMotor, elementLifterServo, elementLifterClawServo);
        spinner = new Spinner(spinnerMotor);
        robot.register(drivetrain, intake, freightArm, elementLifter, spinner);

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        configureButtons();
    }

    @Override
    public void matchStart() {

    }

    @Override
    public void debugLoop(){
        telemetry.addData("arm-encoder", armMotor.getCurrentPosition());
    }

    public void configureButtons(){
        intakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.A)).whenPressed(intake::intake).whenReleased(intake::stop);
        outtakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.B)).whenPressed(intake::outtake).whenReleased(intake::stop);

        Button lifterUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)).whenPressed(elementLifter::boomUp).whenReleased(elementLifter::stop);
        Button lifterDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A)).whenPressed(elementLifter::boomDown).whenReleased(elementLifter::stop);

        boomUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)).whenPressed(freightArm::boomUp).whenReleased(freightArm::stop);
        boomDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)).whenPressed(freightArm::boomDown).whenReleased(freightArm::stop);

        armServoButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT)).whenPressed(freightArm::boxServoFlip);
        armServoButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT)).whenPressed(freightArm::armServoFlip);

        spinButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)).whenPressed(spinner::spin).whenReleased(spinner::stop);
        robot.schedule(new MecDriveCommand(drivetrain, driverGamepad));
    }
}
