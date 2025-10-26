package patterns.car_rental.filters;

import patterns.car_rental.entities.Car;

import java.util.List;

public class MakerCriteria implements CarSearchCriteria<Car>{
    private final String maker;

    public MakerCriteria(String maker){
        this.maker = maker;
    }

    @Override
    public List<Car> meetCriteria(List<Car> cars) {
        return cars.stream().filter(car -> car.getMake().equalsIgnoreCase(this.maker)).toList();
    }
}
