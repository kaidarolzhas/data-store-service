package kz.olzhas.kafka.datastoremicroservice.config;

import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;

public class RedisSchema {

    //set
    public static String sensorKeys() {
        return KeyHelper.getKey("sensors");
    }

    //hash with summary types
    public static String summaryKey(
            long sensorId,
            MeasurementType measurementType
    ) {
        return KeyHelper.getKey("sensors:" + sensorId + ":" + measurementType.name().toLowerCase());
    }

    //app:sensors:1:power
    //avg: 1
    //min: 0.1
    //max: 11
    //sum: 123
}
