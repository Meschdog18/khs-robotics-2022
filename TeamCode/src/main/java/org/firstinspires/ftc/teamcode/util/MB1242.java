package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.I2cSensor;

@I2cSensor(name = "MB1242 Ultrasonic Sensor", description = "Ultrasonic Sensor from MaxBotix", xmlTag = "MB1242")
public class MB1242 extends I2cDeviceSynchDevice<I2cDeviceSynch> {
    public MB1242(I2cDeviceSynch deviceClient){
        super(deviceClient, true);

        super.registerArmingStateCallback(false);
        this.deviceClient.engage();
    }
    @Override
    protected synchronized boolean doInitialize() {
        return true;
    }

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Other;
    }

    @Override
    public String getDeviceName() {
        return "MB1242 I2CXL-MaxSonar-EZ4";
    }
}
