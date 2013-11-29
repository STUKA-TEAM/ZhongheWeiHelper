package weather;

import java.util.ArrayList;

public class Result {
private String currentCity;
private ArrayList<WeatherData> weather_data;
public String getCurrentCity() {
	return currentCity;
}
public void setCurrentCity(String currentCity) {
	this.currentCity = currentCity;
}
public ArrayList<WeatherData> getWeather_data() {
	return weather_data;
}
public void setWeather_data(ArrayList<WeatherData> weather_data) {
	this.weather_data = weather_data;
}

}
