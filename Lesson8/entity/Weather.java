package Lesson7.entity;

public class Weather {
    private String city;
    private String weatherText;
    private double temperature;

    public Weather(String city, String weatherText, double temperature) {
        this.city = city;
        this.weatherText = weatherText;
        this.temperature = temperature;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String localDate) {
        this.weatherText = weatherText;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", localDate='" + weatherText + '\'' +
                ", temperature=" + String.format("%.2f", temperature) +
                '}';
    }
}
