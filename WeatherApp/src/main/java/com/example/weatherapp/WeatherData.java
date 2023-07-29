package com.example.weatherapp;

public class WeatherData {
  private String city;
  private String temperature;
  private String description;
  private int humidity;
  private double latitude;
  private double longitude;

  public WeatherData(String city, String temperature, String description, int humidity, double latitude, double longitude) {
    this.city = city;
    this.temperature = temperature;
    this.description = description;
    this.humidity = humidity;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getCity() {
    return city;
  }

  public String getTemperature() {
    return temperature;
  }

  public String getDescription() {
    return description;
  }

  public int getHumidity() {
    return humidity;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
