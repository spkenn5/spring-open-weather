package com.oddle.test.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oddle.test.model.Weather;
import com.oddle.test.service.OpenWeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private OpenWeatherService apiService;

	@GetMapping("/list")
	public String listWeather(Model model) {

		List<Weather> weatherList = apiService.getWeatherLogs();
		model.addAttribute("weather", weatherList);
		return "index";
	}

	@RequestMapping("/searchForecast")
	public String searchWeatherForecast() {
		return "weather-forecast-home";
	}

	@RequestMapping("/searchCurrent")
	public String searchCurrentWeather() {
		return "weather-current-home";
	}

	@RequestMapping("/getWeather")
	public String getWeather(@RequestParam("cityName") String cityName, Model model) {
		String encoded;
		try {
			encoded = URLEncoder.encode(cityName, "UTF-8");
		} catch (UnsupportedEncodingException ignored) {
			encoded = cityName.replaceAll(" ", "%20");
		}
		List<Weather> weatherList = apiService.getWeatherForecast(encoded);

		model.addAttribute("weatherList", weatherList);

		return "weather-forecast-response";
	}

	@RequestMapping("/getCurrentWeather")
	public String getCurrentWeather(@RequestParam("cityName") String cityName, Model model) {
		String encoded;
		try {
			encoded = URLEncoder.encode(cityName, "UTF-8");
		} catch (UnsupportedEncodingException ignored) {
			encoded = cityName.replaceAll(" ", "%20");
		}

		Weather w = apiService.getCurrentWeather(encoded);

		model.addAttribute("weather", w);

		return "weather-current-response";
	}

	@GetMapping("/add")
	public String addNewWeather(@RequestParam("weatherObj") String weatherObj) {
		String[] csv = weatherObj.split(",");
		Weather weather = new Weather();
		weather.setCity(csv[0]);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
		try {
			weather.setDate(sdf.parse(csv[1]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		weather.setTemperature(Double.parseDouble(csv[2]));
		weather.setHumidity(Double.parseDouble(csv[3]));
		weather.setPressure(Double.parseDouble(csv[4]));
		weather.setWindSpeed(Double.parseDouble(csv[5]));

		apiService.addNewWeather(weather);
		return "redirect:/weather/list";
	}

	@GetMapping("/delete")
	public String deleteWeather(@RequestParam("weatherId") int id) {
		apiService.deleteWeather(id);

		return "redirect:/weather/list";
	}

}
