package patterns.behavioral.observer.weather_station;

interface WeatherObserver {
    void update(WeatherConditions conditions);
}

class CurrentConditionDisplay implements  WeatherObserver{

    private WeatherConditions conditions;

    @Override
    public void update(WeatherConditions conditions) {
        this.conditions = conditions;
        display();
    }

    public void display(){
        System.out.println("Current conditions: " +
                           conditions.getTemperature() + "°C, " +
                           conditions.getHumidity() + "% humidity, " +
                           conditions.getPressure() + " hPa pressure");
    }
}

class ForecastDisplay implements WeatherObserver {

    private WeatherConditions conditions;

    @Override
    public void update(WeatherConditions conditions) {
        this.conditions = conditions;
        display();
    }

    public void temperatureForecast(){
        if(conditions.temperature<10){
            System.out.println("It will be chilly today.");
        } else if(conditions.temperature<30){
            System.out.println("The temperature will be moderate.");
        } else {
            System.out.println("It is going to be hot today.");
        }
    }

    public void humidityForecast(){
        if(conditions.humidity<30){
            System.out.println("The air will be dry today.");
        } else if(conditions.humidity>70){
            System.out.println("Expect high humidity today.");
        } else {
            System.out.println("The humidity will be moderate.");
        }
    }

    public void pressureForecast(){
        if(conditions.pressure<1000){
            System.out.println("Expect low pressure today.");
        } else if(conditions.pressure>1020){
            System.out.println("The pressure will be high today.");
        } else {
            System.out.println("The pressure will be normal.");
        }
    }

    public void display() {
        System.out.println("Forecast based on current conditions:");
        temperatureForecast();
        humidityForecast();
        pressureForecast();
    }
}