package com.deloitte.training.demo4.services;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.training.demo4.model.Car;
import com.deloitte.training.demo4.model.CarList;
import com.deloitte.training.demo4.model.ServiceOptions;

@RestController("carsController")
@RequestMapping("/cars")
public class CarsController {
    private Map<String, Car> stock;

    @Resource(name = "stock")
    public void setStock(Map<String, Car> stock) {
        this.stock = stock;
    }

//    @GetMapping(produces = "application/xml")
//    public ResponseEntity<CarList> viewAllAsXml(@RequestParam("serviceOptions") Optional<ServiceOptions> serviceOptions) {
//        if (stock.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            Collection<Car> cars = new HashSet<>(stock.values());
//            serviceOptions.ifPresent(options -> cars.removeIf(car -> !car.getServiceOptions().equals(options)));
//            return ResponseEntity.ok(new CarList(cars));
//        }
//    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Collection<Car>> viewAllAsJson(@RequestParam("serviceOptions") Optional<ServiceOptions> serviceOptions) {
        if (stock.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Collection<Car> cars = new HashSet<>(stock.values());
            serviceOptions.ifPresent(options -> cars.removeIf(car -> !car.getServiceOptions().equals(options)));
            HttpHeaders headers = new HttpHeaders();
            headers.set("NumCars", String.valueOf(cars.size()));
            return new ResponseEntity<>(cars, headers, OK);
        }
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(value = "/{model}", consumes = "application/json")
    public void addOrUpdateCareViaJson(@RequestBody Car newCar) {
        stock.put(newCar.getModel(), newCar);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(value = "/{model}", consumes = "application/xml")
    public void addOrUpdateCareViaXml(@RequestBody Car newCar) {
        stock.put(newCar.getModel(), newCar);
    }

    @GetMapping(value = "/{model}", produces = "application/json")
    public ResponseEntity<Car> fetchCarDetailsAsJson(@PathVariable("model") String model) {
        if (stock.containsKey(model)) {
            return ResponseEntity.ok(stock.get(model));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{model}", produces = "application/xml")
    public ResponseEntity<Car> fetchCarDetailsAsXml(@PathVariable("model") String model) {
        if (stock.containsKey(model)) {
            return ResponseEntity.ok(stock.get(model));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{model}")
    public ResponseEntity<String> deleteACar(@PathVariable("model") String model) throws Exception {
        if (stock.containsKey(model)) {
            if (stock.get(model).getModel().equalsIgnoreCase("Mercedes")) {
                throw new Exception("Cannot remove Mercedes!");
            }
            stock.remove(model);
            return ResponseEntity.ok("[\"Removed " + model + "\"]");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
