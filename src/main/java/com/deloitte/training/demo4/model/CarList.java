package com.deloitte.training.demo4.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listOfCars")
public class CarList {
    private Collection<Car> cars;

    public CarList() {
    }

    public CarList(Collection<Car> cars) {
        this.cars = cars;
    }

    @XmlElement(name = "entry")
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
