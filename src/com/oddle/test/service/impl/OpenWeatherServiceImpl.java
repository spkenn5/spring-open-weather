package com.oddle.test.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddle.test.dao.WeatherDAO;
import com.oddle.test.model.Weather;
import com.oddle.test.service.HttpService;
import com.oddle.test.service.OpenWeatherService;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

	private static final String API_KEY = "c7329de64542ac964cb71d550115aa07";
	private String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=%s&mode=json&APPID=%s";
	private String BASE_URL_CURRENT = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";

	@Autowired
	private WeatherDAO weatherDAO;
	
	@Autowired
	HttpService httpService;

	@Override
	public List<Weather> getWeatherForecast(String city) {
		DecimalFormat df = new DecimalFormat("#.00");
	    
		List<Weather> list = new ArrayList<>();
		
		String uri = String.format(BASE_URL, city, API_KEY);
		System.out.println("Getting from HTTP Util");
		String response = httpService.getHttpResponse(uri);

		// Map results to JSON
		JSONObject obj = new JSONObject(response);
		String cityName = obj.getJSONObject("city").get("name").toString();
		JSONArray arr = new JSONArray(obj.getJSONArray("list").toString());
		
		for (int i = 0; i < arr.length(); i++) {
			Weather weather = new Weather();
			weather.setCity(cityName);
			JSONObject main = arr.getJSONObject(i).getJSONObject("main");
			JSONObject wind = arr.getJSONObject(i).getJSONObject("wind");
			weather.setPressure(main.getDouble("pressure"));
			weather.setHumidity(main.getDouble("humidity"));
			weather.setTemperature(Double.valueOf(df.format(main.getDouble("temp") - (273.15))));
			weather.setWindSpeed(wind.getDouble("speed"));
			Date date = new Date((arr.getJSONObject(i).getLong("dt") * 1000));
			weather.setDate(date);
			list.add(weather);
		}
		
		return list;
	}

	@Override
	@Transactional
	public List<Weather> getWeatherLogs() {
		return weatherDAO.getWeatherLogs();
	}

	@Override
	public Weather getCurrentWeather(String city) {
		DecimalFormat df = new DecimalFormat("#.00");
		
		String uri = String.format(BASE_URL_CURRENT, city, API_KEY);
		System.out.println("Getting from HTTP Util -> " + uri);
		String response = httpService.getHttpResponse(uri);
		
		// Map results to JSON
		JSONObject obj = new JSONObject(response);
		String cityName = obj.getString("name").toString();

		Weather weather = new Weather();
		weather.setCity(cityName);
		JSONObject main = obj.getJSONObject("main");
		JSONObject wind = obj.getJSONObject("wind");
		weather.setPressure(main.getDouble("pressure"));
		weather.setHumidity(main.getDouble("humidity"));
		weather.setTemperature(Double.valueOf(df.format(main.getDouble("temp") - (273.15))));
		weather.setWindSpeed(wind.getDouble("speed"));
		Date date = new Date((obj.getLong("dt") * 1000));
		weather.setDate(date);
		
		return weather;
	}

	@Override
	@Transactional
	public void addNewWeather(Weather weather) {
		weatherDAO.addWeather(weather);		
	}

	@Override
	@Transactional
	public void deleteWeather(int id) {
		weatherDAO.deleteWeatherById(id);		
	}

}
