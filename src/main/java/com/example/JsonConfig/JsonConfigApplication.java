package com.example.JsonConfig;

import com.jayway.jsonpath.JsonPath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonConfigApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JsonConfigApplication.class, args);
		System.out.println("Welcome to Gson !");

		editJson();
//		readJson();

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

//	@RequestMapping(value = "/foos", method = RequestMethod.GET)
//	@ResponseBody
//	public String getFoosBySimplePath() {
//		return "Get some Foos";
//	}
//
//	@GetMapping("/json")
//	@ResponseStatus(value = HttpStatus.OK)
//	public static void readJson() throws Exception {
//		String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/ConfigSimpleWithGson.json";
//		String jsonConfig = readFileAsString(file);
//		System.out.println(jsonConfig);
//
//	}


	public static String readFileAsString(String file) throws Exception
	{

		return new String(Files.readAllBytes(Paths.get(file)));
	}

//	public static String readFileAsString(String file) throws Exception
//	{
//		return new String(Files.readAllBytes(Paths.get(file)));
//	}


//	String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/ConfigSimpleWithGson.json";
//	String jsonConfig = readFileAsString(file);
//        System.out.println(jsonConfig);

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
