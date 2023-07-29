package com.example.weatherapp;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherApp extends Application {
  private WebView mapView;
  private WebEngine webEngine;
  private Label resultLabel;

  @Override
  public void start(Stage primaryStage) {
    TextField cityField = new TextField();
    resultLabel = new Label();
    mapView = new WebView();
    webEngine = mapView.getEngine();

    Button getWeatherButton = new Button("Get Weather");
    getWeatherButton.setOnAction(e -> getWeatherDataAndUpdateUI(cityField.getText().trim()));

    cityField.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        getWeatherDataAndUpdateUI(cityField.getText().trim());
      }
    });

    VBox root = new VBox(10, cityField, getWeatherButton, resultLabel, mapView);
    root.setAlignment(Pos.CENTER);
    Scene scene = new Scene(root, 800, 800);

    primaryStage.setTitle("Weather App");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void getWeatherDataAndUpdateUI(String city) {
    if (!city.isEmpty()) {
      try {
        WeatherData data = WeatherAPI.getWeatherData(city);
        String result = "Город: " + data.getCity() + "\nТемпература: " + data.getTemperature()
            + "\nОписание: " + data.getDescription() + "\nВлажность: " + data.getHumidity() + "%";
        resultLabel.setText(result);

        // Получаем координаты города и показываем его на карте
        double latitude = data.getLatitude();
        double longitude = data.getLongitude();
        showCityOnMap(latitude, longitude);
      } catch (IOException ex) {
        resultLabel.setText("Error fetching weather data. Please check the city name.");
      }
    }
  }

  private void showCityOnMap(double latitude, double longitude) {
    webEngine.load("https://www.openstreetmap.org/?mlat=" + latitude + "&mlon=" + longitude + "#map=10/" + latitude + "/" + longitude);
  }

  public static void main(String[] args) {
    launch(args);
  }
}


