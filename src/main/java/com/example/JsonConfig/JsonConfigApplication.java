package com.example.JsonConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonConfigApplication.class, args);
		System.out.println("Welcome to Gson !");

		//serializeConfigSimple();
		deserializeConfigSimple();
	}


	private static void serializeConfigSimple() {

		Plotly config = new Plotly(
				"0.0.0.0",
				8051,
				true,
				"KBA - Dashboard"
		);
		Gson gson = new Gson();
		String json = gson.toJson(config);

	}

	private static void deserializeConfigSimple() {
		String configJson = "{'host':'0.0.0.0','port':8051,'debug':true,'title':'KBA - Dashboard'}";

		Gson gson = new Gson();
		Plotly configSimple = gson.fromJson(configJson, Plotly.class);

	}
}
