package com.example.JsonConfig;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonConfigApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JsonConfigApplication.class, args);
		System.out.println("Welcome to Gson !");

//		serializeConfigSimple();
//		deserializeConfigNested();
		editJson();
	}

//no exception implemented !

	private static void editJson() throws IOException {
		String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/Config.json";
		String json = new String(Files.readAllBytes(Paths.get(file)));
//		String pathToString = "$.plotly.boxes.name1";
		String newJson = JsonPath.parse(json).set( "$.es.srv.index", "test").jsonString();
		Files.write(Paths.get(file), newJson.getBytes());
		System.out.println(json);
	}

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
//at the moment it does create an object but doesnt read
	}

	public static String readFileAsString(String file) throws Exception
	{
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}
