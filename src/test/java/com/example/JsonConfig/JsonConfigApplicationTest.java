package com.example.JsonConfig;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("rawtypes")

class JsonConfigApplicationTest {

    public final String configJson = "src/test/testResources/ConfigSimpleWithGson.json";



    @Test
    void mapSerialization() {
        System.out.println(configJson);
        try {
            byte[] actualOutput = JsonConfigApplication.mapSerialization();
            Map<String, Object> configMap = JsonConfigApplication.mapJson();
            byte[] expectedOutput = SerializationUtils.serialize((Serializable) configMap);

            assertArrayEquals(actualOutput, expectedOutput);

        } catch (IOException e) {
            System.out.println("Your file cant be serialize " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void mapDeserialization() {
        try {
            Object actualOutput = JsonConfigApplication.mapDeserialization();
            Object exceptedOutput = JsonConfigApplication.mapJson();


            assertEquals(exceptedOutput, actualOutput);

        } catch (IOException e) {
            System.out.println("I cant deserialize the map" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void mapJson() {
        try {
            Map actualOutput = JsonConfigApplication.mapJson();
            String loc = "/home/fo/IdeaProjects/NCA_jsonConfig/src/main/java/com/example/JsonConfig/Config.json";
            String jsonConfig = JsonConfigApplication.readFileAsString(loc);
            Map expectedOutput = new Gson().fromJson(jsonConfig, Map.class);


            assertEquals(actualOutput, expectedOutput);

        } catch (Exception e) {
            System.out.println("I cant do the map from current file" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFileAsString() {
        try {
            String actualOutput = JsonConfigApplication.readFileAsString((configJson));
            String expectedOutput = (Files.readString(Paths.get(configJson)));

            assertEquals(expectedOutput, actualOutput);


        } catch (IOException e) {
            System.out.println("I cant read that file as string" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void editJson() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(configJson)));
            String actualOutput = JsonConfigApplication.readFileAsString(configJson);
            String expectedOutput =  JsonPath.parse(json).set("$.plotly.host", "ffffff").jsonString();

            if (!actualOutput.equals(expectedOutput)) {
                System.out.println("File edited");

            } else {
                System.out.println("Your file are equals");

            }

            assertNotEquals(actualOutput,expectedOutput);

        } catch (Exception e) {
            System.out.println("Your file doesnt equals" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Test
    void readValue() throws IOException {

        File configMap = new File("src/test/testResources/ConfigSimpleWithGson.json");

        String modifiedAttribute = "test";

        String expectedOutput = JsonPath.parse(configMap).set("$.plotly.host", modifiedAttribute).jsonString();
        Object thisString = JsonPath.parse(expectedOutput).read("$.plotly.host");
        String actualOutput = JsonConfigApplication.readValue();

        assertEquals(thisString,actualOutput);
    }
}
