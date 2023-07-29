package com.example.weatherapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
  private static final String API_KEY = "53cd31ad64a611369ae4824262eb4bcd"; // Замените YOUR_API_KEY своим ключом API

  public static WeatherData getWeatherData(String city) throws IOException {
    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    JSONObject json = new JSONObject(response.toString());
    String cityName = json.getString("name");
    double temperatureInKelvin = json.getJSONObject("main").getDouble("temp");
    String temperature = formatTemperature(temperatureInKelvin);
    String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
    int humidity = json.getJSONObject("main").getInt("humidity");
    double latitude = json.getJSONObject("coord").getDouble("lat");
    double longitude = json.getJSONObject("coord").getDouble("lon");

    return new WeatherData(cityName, temperature, description, humidity, latitude, longitude);
  }

  public static WeatherData[] getWeatherForecast(String city) throws IOException {
    String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    JSONObject json = new JSONObject(response.toString());
    // Добавьте код для обработки прогноза погоды и получения данных для нескольких дней
    // (например, температура, описание и влажность для каждого дня)
    // и верните массив объектов WeatherData, представляющих прогноз погоды.

    return null;
  }

  private static String formatTemperature(double temperatureInKelvin) {
    // Конвертируем температуру из Кельвинов в Цельсии и округляем до одной десятой
    double temperatureInCelsius = temperatureInKelvin - 273.15;
    return String.format("%.1f градусов", temperatureInCelsius);
  }
}

