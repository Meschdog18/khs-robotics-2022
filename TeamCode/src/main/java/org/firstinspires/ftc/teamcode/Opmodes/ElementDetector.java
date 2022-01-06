package org.firstinspires.ftc.teamcode.Opmodes;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

//check if element is blocking mat, instead of tape, so just check for grey instead of blue/red

public class ElementDetector extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();

    static final Rect LEFT_BARCODE = new Rect(
            new Point(20, 35),
            new Point(70, 75));

    static final Rect MIDDLE_BARCODE = new Rect(
            new Point(132, 35),
            new Point(182, 75));

    static final Rect RIGHT_BARCODE = new Rect(
            new Point(250, 35),
            new Point(300, 75));

    public ElementDetector(Telemetry t){telemetry = t;}

    @Override
    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(110, 50, 50);
        Scalar highHSV = new Scalar(130, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_BARCODE);
        Mat middle = mat.submat(MIDDLE_BARCODE);
        Mat right = mat.submat(RIGHT_BARCODE);

        double leftValue = Core.sumElems(left).val[0] / LEFT_BARCODE.area() / 255;
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_BARCODE.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_BARCODE.area() / 255;

        left.release();
        middle.release();
        right.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(middle).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(middleValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
        telemetry.update();
        Long leftPercent = Math.round(leftValue * 100);
        Long middlePercent = Math.round(middleValue * 100);
        Long rightPercent = Math.round(rightValue * 100);

        Long[] values = {Math.round(leftValue * 100), Math.round(middleValue * 100), Math.round(rightValue * 100)};
        Long min = Collections.min(Arrays.asList(new Long[] {leftPercent, middlePercent, rightPercent}));

        String pos = new String();

        if(leftPercent == min){
            pos = "left";
        }
        if(middlePercent == min){
            pos = "middle";
        }
        if(rightPercent == min){
            pos = "right";
        }
        telemetry.addData("position", pos);

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorBarcode = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, LEFT_BARCODE, colorBarcode);
        Imgproc.rectangle(mat, MIDDLE_BARCODE, colorBarcode);
        Imgproc.rectangle(mat, RIGHT_BARCODE, colorBarcode);

        return mat;
    }

}
