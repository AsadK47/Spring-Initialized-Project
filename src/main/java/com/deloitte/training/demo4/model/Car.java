package com.deloitte.training.demo4.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
public class Car {
    private String model;
    private String colour;
    private int year;
    private ServiceOptions serviceOptions;


    public Car(String model, String colour, int year, ServiceOptions serviceOptions) {
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.serviceOptions = serviceOptions;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ServiceOptions getServiceOptions() {
        return serviceOptions;
    }

    public void setServiceOptions(ServiceOptions serviceOptions) {
        this.serviceOptions = serviceOptions;
    }
}
