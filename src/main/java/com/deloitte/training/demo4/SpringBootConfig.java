package com.deloitte.training.demo4;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.deloitte.training.demo4.model.Car;

import static com.deloitte.training.demo4.model.ServiceOptions.BASIC;
import static com.deloitte.training.demo4.model.ServiceOptions.SUPER;
import static com.deloitte.training.demo4.model.ServiceOptions.ULTRA;

@Configuration
public class SpringBootConfig {
    @Bean(name = "stock")
    @Scope("prototype")
    public Map<String, Car> buildStock() {
        HashMap<String, Car> stock = new HashMap<>();
        addStockToMap(new Car("Mercedes", "Blue", 2010, ULTRA), stock);
        addStockToMap(new Car("BMW", "Black", 2005, SUPER), stock);
        addStockToMap(new Car("Toyota", "Green", 2000, BASIC), stock);
        addStockToMap(new Car("Mclaren", "Orange", 1995, ULTRA), stock);
        addStockToMap(new Car("Hyundai", "Purple", 2015, BASIC), stock);
        addStockToMap(new Car("Tesla", "Silver", 2018, ULTRA), stock);
        addStockToMap(new Car("Ford", "Blue", 2002, BASIC), stock);
        addStockToMap(new Car("Mitsubishi", "Blue", 2020, SUPER), stock);

        return stock;
    }

    private void addStockToMap(Car car, Map<String, Car> cars) {
        cars.put(car.getModel(), car);
    }
}
