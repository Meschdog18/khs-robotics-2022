package org.firstinspires.ftc.teamcode.Vision;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.Collections;

public class ElementPipeline extends OpenCvPipeline {
    private final int threshold = 5;
    private boolean debug = false;
    static final Scalar GREEN = new Scalar(0, 255, 0);

    static final Rect LEFT_BARCODE = new Rect(
            new Point(20, 75),
            new Point(70, 135));

    static final Rect MIDDLE_BARCODE = new Rect(
            new Point(132, 75),
            new Point(182, 135));

    static final Rect RIGHT_BARCODE = new Rect(
            new Point(250, 75),
            new Point(300, 135));

    public ElementPipeline(){}

    public ElementPipeline(Boolean debug){
        this.debug = debug;
    }
    Mat regionLeft, regionMiddle, regionRight;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int leftAvg, middleAvg, rightAvg;

    // Volatile since accessed by OpMode thread w/o synchronization

    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }

    @Override
    public void init(Mat firstFrame)
    {

        inputToCb(firstFrame);

        /*
         * Submats are a persistent reference to a region of the parent
         * buffer. Any changes to the child affect the parent, and the
         * reverse also holds true.
         */
        regionLeft = Cb.submat(LEFT_BARCODE);
        regionMiddle = Cb.submat(MIDDLE_BARCODE);
        regionRight = Cb.submat(RIGHT_BARCODE);
    }

    @Override
    public Mat processFrame(Mat input) {

        inputToCb(input);

        /*
         * Compute the average pixel value of each submat region. We're
         * taking the average of a single channel buffer, so the value
         * we need is at index 0. We could have also taken the average
         * pixel value of the 3-channel image, and referenced the value
         * at index 2 here.
         */
        leftAvg = (int) Core.mean(regionLeft).val[0];
        middleAvg = (int) Core.mean(regionMiddle).val[0];
        rightAvg = (int) Core.mean(regionRight).val[0];

        if (debug) {
            int max = Math.min(Math.min(leftAvg, middleAvg), rightAvg);

            if (max == leftAvg)
            {


                Imgproc.rectangle(
                        input, // Buffer to draw on
                        LEFT_BARCODE,
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            } else if (max == middleAvg)
            {


                Imgproc.rectangle(
                        input, // Buffer to draw on
                        MIDDLE_BARCODE,
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            } else if (max == rightAvg)
            {


                Imgproc.rectangle(
                        input, // Buffer to draw on
                        RIGHT_BARCODE,
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            }
        }

        Scalar colorBarcode = new Scalar(0, 255, 0);

        Imgproc.rectangle(input, LEFT_BARCODE, colorBarcode);
        Imgproc.rectangle(input, MIDDLE_BARCODE, colorBarcode);
        Imgproc.rectangle(input, RIGHT_BARCODE, colorBarcode);

        return input;
    }

    public int getLeftAvg(){
        return leftAvg;
    }

    public int getMiddleAvg(){
        return middleAvg;
    }

    public int getRightAvg(){
        return rightAvg;
    }



}
