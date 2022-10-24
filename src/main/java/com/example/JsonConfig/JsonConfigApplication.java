package com.example.JsonConfig;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonConfigApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JsonConfigApplication.class, args);
		System.out.println("Welcome to Gson !");

		//serializeConfigSimple();
		deserializeConfigNested();
	}

//no exception implemented !
	private static void serializeConfigNested() {
		Plotly configSimple = new Plotly(
				"0.0.0.0",
				8051,
				true,
				"KBA-Dashboard"
		);

		Boxes boxes = new Boxes("1","2","3","4");

		// Gson gson = new Gson();
		//simple code with Gson
		String json = new Gson().toJson(boxes);
	}

	private static void deserializeConfigNested() throws Exception {

//        json path from config to configSimple changed
		String file = "/home/ds/IdeaProjects/ClassConfig/src/main/java/org/example/ConfigSimple.json";
		String jsonConfig = readFileAsString(file);

//        String jsonConfig = "{ 'plotly':{ 'host':'0.0.0.0', 'port':8051, 'debug':true, 'title':'KBA - Dashboard', 'boxes':{ 'name1':'1', 'name2':'2', 'name3':'3', 'name4':'4'}}}";
		Config config = new Gson().fromJson(jsonConfig, Config.class);

	}

	public static String readFileAsString(String file) throws Exception
	{
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}
