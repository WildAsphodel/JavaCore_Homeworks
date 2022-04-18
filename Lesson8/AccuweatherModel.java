package Lesson7;

import Lesson7.entity.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "LybOis5wipwMUhYawuTDZ7j3uQHyKyKx";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private String city;
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getCity() {
        return city;
    }

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException, SQLException {
        this.city = selectedCity;
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                Double temperature = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature")
                        .at("/Maximum").at("/Value").asDouble();
                String weatherText = objectMapper.readTree(weatherResponse).at("/Headline").at("/Text").asText();
               System.out.println("In " + selectedCity + " " + weatherText + ". Temperature: " + temperature + " C");
               dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity, weatherText, temperature));
                break;
            case FIVE_DAYS:
                HttpUrl httpUrlFiveDays = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("metric", "true")
                        .build();

                Request requestFiveDays = new Request.Builder()
                        .url(httpUrlFiveDays)
                        .build();
                Response fiveDaysForecastResponse = okHttpClient.newCall(requestFiveDays).execute();
                String weatherFiveDaysResponse = fiveDaysForecastResponse.body().string();

                ObjectMapper objectMapper1 = new ObjectMapper();


                List<Weather> weatherList = new ArrayList<>();
                for (int i = 0; i <5; i++) {
                    Double temperatureFiveDays = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(i).at("/Temperature")
                            .at("/Maximum").at("/Value").asDouble();
                    String weatherTextFiveDays = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(i)
                            .at("/Day").at("/IconPhrase").asText();
                    weatherList.add(new Weather(selectedCity, weatherTextFiveDays,temperatureFiveDays));
                    System.out.println("In " + selectedCity + " " + weatherTextFiveDays + ". Temperature: " + temperatureFiveDays + " C");
                }
                dataBaseRepository.saveWeatherToDataBase(weatherList);
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
