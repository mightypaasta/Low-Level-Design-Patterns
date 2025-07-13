package patterns.behavioral.observer.weather_station;

import java.util.ArrayList;

interface WeatherObservable {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}

abstract class WeatherConditions{
    protected float temperature;
    protected float humidity;
    protected float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

}

class WeatherStation extends WeatherConditions implements WeatherObservable {
    private final ArrayList<WeatherObserver> weatherObservers;

    public WeatherStation() {
        this.weatherObservers = new ArrayList<>();
    }

    public void addObserver(WeatherObserver observer) {
        if (observer != null && !weatherObservers.contains(observer)) {
            weatherObservers.add(observer);
        }else{
            throw new IllegalArgumentException("WeatherObserver cannot be null or already exists");
        }
    }

    public void removeObserver(WeatherObserver observer) {
        if (observer != null && weatherObservers.contains(observer)) {
            weatherObservers.remove(observer);
        } else {
            throw new IllegalArgumentException("WeatherObserver cannot be null or does not exist");
        }
    }

    public void notifyObservers() {
        for (WeatherObserver observer : weatherObservers) {
            observer.update(this);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

}
