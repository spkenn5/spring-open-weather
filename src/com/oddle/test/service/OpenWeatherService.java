package com.oddle.test.service;

import java.util.List;

import com.oddle.test.model.Weather;

public interface OpenWeatherService {
	public List<Weather> getWeatherForecast(String city);
	public Weather getCurrentWeather(String city);
	public List<Weather> getWeatherLogs();
	public void addNewWeather(Weather weather);
	public void deleteWeather(int id);

}
