package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Vision.ElementDetector;

//just a note, camera takes like 5 seconds to init
public class Vision extends SubsystemBase{
    private Telemetry telemetry;
    private ElementDetector elementDetector;
    private ElementDetector.Element currentLocation;

    public Vision(HardwareMap hw, String webcamName, Telemetry tl){
        elementDetector = new ElementDetector(hw, webcamName);
        elementDetector.init();
        currentLocation = elementDetector.getElementLocation();
        telemetry = tl;
    }

    @Override
    public void periodic(){
        elementDetector.debug(telemetry);
        if(telemetry != null){
            telemetry.addData("Location:", currentLocation); //only updated when someone call getCurrentLocation()
        }
    }

    public ElementDetector.Element getCurrentLocation(){
        //waits until camera actually starts returning data, maybe find a better solution
        //acts like a safety net in case robot is run right away
        while(!elementDetector.isInitialized()){
            try{
                Thread.sleep(50);
            }
            catch (final InterruptedException ex){
                break;
            }
        }
        currentLocation = elementDetector.getElementLocation();
        return currentLocation;
    }
}
