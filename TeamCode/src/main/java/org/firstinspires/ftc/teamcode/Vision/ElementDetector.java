package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class ElementDetector {
    private OpenCvCamera camera;
    private ElementPipeline elementPipeline;

    public ElementDetector(HardwareMap hw, String webcamName){
        int cameraMonitorViewId = hw.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hw.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hw.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
    }

    public void init(){
        camera.setPipeline(elementPipeline = new ElementPipeline(true));

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }
    public Element getElementLocation(){
        int max = Math.min(Math.min(elementPipeline.getLeftAvg(), elementPipeline.getMiddleAvg()), elementPipeline.getRightAvg());

        if(max == elementPipeline.getLeftAvg()){
            return Element.LEFT;
        }
        else if(max == elementPipeline.getMiddleAvg()){
            return Element.MIDDLE;
        }
        else {
            return Element.RIGHT;
        }

    }
    public boolean isInitialized(){
        return (elementPipeline.getLeftAvg() != 0 || elementPipeline.getMiddleAvg() != 0 || elementPipeline.getRightAvg() != 0);
    }
    public void debug(Telemetry tl){
        tl.addData("Loc", getElementLocation());
        tl.addData("Left", elementPipeline.getLeftAvg());
        tl.addData("Middle", elementPipeline.getMiddleAvg());
        tl.addData("Right", elementPipeline.getRightAvg());
    }
    public enum Element {
        LEFT,
        MIDDLE,
        RIGHT,
    }
}
