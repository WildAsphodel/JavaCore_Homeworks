package Lesson7;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
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

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException {
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
                String temperatureMin = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureMax = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String weatherText = objectMapper.readTree(weatherResponse).at("/Headline").at("/Text").asText();
               System.out.println("In " + selectedCity + " " + weatherText + ". Temperature: " + temperatureMin + " C - " + temperatureMax + " C");
                //dataBaseRepository.saveWeatherToDataBase(new Weather()) - тут после парсинга добавляем данные в БД
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
                String temperatureFirstDayMin = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(0).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureFirstDayMax = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(0).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String temperatureSecondDayMin = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(1).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureSecondDayMax = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(1).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String temperatureThirdDayMin = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(2).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureThirdDayMax = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(2).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String temperatureFourDayMin = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(3).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureFourDayMax = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(3).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String temperatureFiveDayMin = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(4).at("/Temperature")
                        .at("/Minimum").at("/Value").asText();
                String temperatureFiveDayMax = objectMapper1.readTree(weatherFiveDaysResponse).at("/DailyForecasts").get(4).at("/Temperature")
                        .at("/Maximum").at("/Value").asText();
                String weatherFiveDaysText = objectMapper1.readTree(weatherFiveDaysResponse).at("/Headline").at("/Text").asText();
                System.out.println("In " + selectedCity + " " + weatherFiveDaysText + "\n" +
                        " Temperature 1 day: " + temperatureFirstDayMin + " C - " + temperatureFirstDayMax + " C" + "\n" +
                        " Temperature 2 day: " + temperatureSecondDayMin + " C - " + temperatureSecondDayMax + " C"  + "\n" +
                        " Temperature 3 day: " + temperatureThirdDayMin + " C - " + temperatureThirdDayMax + " C"  + "\n" +
                        " Temperature 4 day: " + temperatureFourDayMin + " C - " + temperatureFourDayMax + " C"  + "\n" +
                        " Temperature 5 day: " + temperatureFiveDayMin + " C - " + temperatureFiveDayMax + " C");
                break;
        }
    }

//    @Override
//    public List<Weather> getSavedToDBWeather() {
//        return dataBaseRepository.getSavedToDBWeather();
//    }

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
