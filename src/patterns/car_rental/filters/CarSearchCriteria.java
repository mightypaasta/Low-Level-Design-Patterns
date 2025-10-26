package patterns.car_rental.filters;

import patterns.car_rental.entities.Car;

import java.util.List;

public interface CarSearchCriteria<T> {
    List<Car> meetCriteria(List<T> items);
}
