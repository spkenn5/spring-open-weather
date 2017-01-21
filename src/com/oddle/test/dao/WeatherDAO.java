package com.oddle.test.dao;

import java.util.List;

import com.oddle.test.model.Weather;

public interface WeatherDAO {
	
	public List<Weather> getWeatherLogs();
	
	public void addWeather(Weather weather);
	
	public void deleteWeather(Weather weather);
	
	public void deleteWeatherById(int id);

}
