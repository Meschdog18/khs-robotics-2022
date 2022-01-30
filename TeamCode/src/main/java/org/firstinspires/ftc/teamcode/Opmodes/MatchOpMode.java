package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCore.Robot;

abstract public class MatchOpMode extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        robot.init();

        waitForStart();

        matchStart();

        while(!isStopRequested() && opModeIsActive()){
            debugLoop();
            robot.run();
            telemetry.update();
        }

        robot.reset();
    }
    public void debugLoop(){};
    public abstract void initialize();
    public abstract void matchStart();
}
