package patterns.car_rental.filters;

import patterns.car_rental.entities.Car;

import java.util.List;
import java.util.stream.Collectors;

public class ModelCriteria implements CarSearchCriteria<Car>{
    private final String model;

    public ModelCriteria(String model){
        this.model = model;
    }


    @Override
    public List<Car> meetCriteria(List<Car> cars) {
        return cars.stream().filter(car -> car.getModel().equalsIgnoreCase(this.model)).toList();
    }
}
