package kz.olzhas.kafka.datastoremicroservice.web.controller;


import kz.olzhas.kafka.datastoremicroservice.model.exception.SensorNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SensorNotFoundException.class)
    public String sensorNotFound(SensorNotFoundException e){
        return "sensor not found";
    }


    @ExceptionHandler
    public String server(Exception e){
        e.printStackTrace();
        return "something happened.";
    }
}
