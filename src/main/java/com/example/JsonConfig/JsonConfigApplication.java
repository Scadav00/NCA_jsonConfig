package com.example.JsonConfig;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonConfigApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JsonConfigApplication.class, args);
		System.out.println("Welcome to Gson !");

//		editJson();
//		readJson();
//		mapJson();

//		String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/Config.json";
//		String json = new String(Files.readAllBytes(Paths.get(file)));
//		convertJsonIntoMap(json);
	}
//no exception implemented !

	private static void editJson() throws IOException {
		String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/Config.json";
		String json = new String(Files.readAllBytes(Paths.get(file)));
//		String pathToString = "$.plotly.boxes.name1";
		String newJson = JsonPath.parse(json).set( "$.plotly.host", "test").jsonString();
		Files.write(Paths.get(file), newJson.getBytes());
		System.out.println(newJson);
	}

//	public static Map<String, Object> convertJsonIntoMap(String jsonFile) {
//		Map<String, Object> map = new HashMap<>();
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//			mapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {
//			});
//			map = mapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return map;
//	}

	private static void mapJson() {

		String json = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/ConfigSimple.json";
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

		Gson gson = builder.create();
		Map map = gson.fromJson(json, Map.class);
		System.out.println(map);
	}

//	String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/ConfigSimple.json";
//	Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
//	Map<String, String[]> son = new Gson().fromJson(file, mapType);
//		System.out.println(son);

	public static String readFileAsString(String file) throws Exception
	{
		return new String(Files.readAllBytes(Paths.get(file)));
	}

//	private static void deserializeConfigNested() throws Exception {
////        json path from config to configSimple changed
//		String file = "/home/ds/IdeaProjects/ClassConfig/src/main/java/org/example/ConfigSimple.json";
//		String jsonConfig = readFileAsString(file);
////        String jsonConfig = "{ 'plotly':{ 'host':'0.0.0.0', 'port':8051, 'debug':true, 'title':'KBA - Dashboard', 'boxes':{ 'name1':'1', 'name2':'2', 'name3':'3', 'name4':'4'}}}";
//		Config config = new Gson().fromJson(jsonConfig, Config.class);
////at the moment it does create an object but doesnt read
//	}
//	public static String readFileAsString(String file) throws Exception
//	{
//		return new String(Files.readAllBytes(Paths.get(file)));
//	}
//}

//	define separate file, define all parameters as constants. call such a message from another project I want to adapt
//	logic

}
