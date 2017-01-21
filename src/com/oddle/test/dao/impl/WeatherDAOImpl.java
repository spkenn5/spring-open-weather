package com.oddle.test.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oddle.test.dao.WeatherDAO;
import com.oddle.test.model.Weather;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Weather> getWeatherLogs() {

		// get hibernate sessions
		Session session = sessionFactory.getCurrentSession();

		// query to get weathers
		Query<Weather> query = session.createQuery("from Weather", Weather.class);
		// execute query
		List<Weather> weathers = query.getResultList();

		// return list of weathers
		return weathers;
	}

	@Override
	public void addWeather(Weather weather) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(weather);
	}

	@Override
	public void deleteWeather(Weather weather) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(weather);		
	}

	@Override
	public void deleteWeatherById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("ID : " + id);
		Query query = currentSession.createQuery("delete from Weather where id=:weatherId");
		query.setParameter("weatherId", id);
		
		query.executeUpdate();
	}

}
