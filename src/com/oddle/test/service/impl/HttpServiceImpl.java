package com.oddle.test.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.oddle.test.service.HttpService;

@Service
public class HttpServiceImpl implements HttpService {

	@Override
	public String getHttpResponse(String uri) {
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if (connection != null) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String input;
					while ((input = br.readLine()) != null) {
						sb.append(input);
					}
					br.close();
					return sb.toString();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
