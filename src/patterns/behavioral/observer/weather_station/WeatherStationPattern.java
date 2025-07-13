package patterns.behavioral.observer.weather_station;

public class WeatherStationPattern {
    public void solve(){
        // This is a placeholder for the solution to Problem 1.
        // The actual implementation would involve creating instances of
        // WeatherStation, CurrentConditionDisplay, and ForecastDisplay,
        // and demonstrating the observer pattern in action.

        System.out.println("Problem 1: Observer Pattern Example");

        // Example usage:
        WeatherStation weatherStation = new WeatherStation();
        CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        weatherStation.addObserver(currentDisplay);
        weatherStation.addObserver(forecastDisplay);

        weatherStation.setMeasurements(25.0f, 65.0f, 1013.0f);
        weatherStation.setMeasurements(31.0f, 71.0f, 1021.0f);
    }
}
