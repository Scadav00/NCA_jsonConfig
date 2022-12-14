package com.example.JsonConfig;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("rawtypes")

class JsonConfigApplicationTest {


    public final String jsonConfig = "/home/fo/IdeaProjects/NCA_jsonConfig/src/main/java/com/example/JsonConfig/ConfigSimpleWithGson.json";


    @Test
    void mapSerialization() {
        try {
            byte[] actualOutput = JsonConfigApplication.mapSerialization();
            Map<String, Object> configMap = JsonConfigApplication.mapJson();
            byte[] expectedOutput = SerializationUtils.serialize((Serializable) configMap);// tu som musel pridat serializable

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

            Map actualOutput = JsonConfigApplication.mapJson(); // z kade vedelo ktori file chcem porovnavat ?
            String loc = "/home/fo/IdeaProjects/NCA_jsonConfig/src/main/java/com/example/JsonConfig/Config.json";
            String jsonConfig = JsonConfigApplication.readFileAsString(loc); //tu som musel pridat jsonConfigapplication
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
            String actualOutput = JsonConfigApplication.readFileAsString(jsonConfig);
            String expectedOutput = (Files.readString(Paths.get(jsonConfig)));

            assertEquals(expectedOutput, actualOutput);


        } catch (IOException e) {
            System.out.println("I cant read that file as string" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Test
    void editJson() {

        try {
            String json = new String(Files.readAllBytes(Paths.get(jsonConfig)));
            String actualOutput = JsonConfigApplication.readFileAsString(jsonConfig);
            String modifiedAttribute = "test";
            String expectedOutput = JsonPath.parse(json).set("$.plotly.host", modifiedAttribute).jsonString();
            //String readAttribute = na zaklade path  vytiahne z expectedOutput(plotly. host,modifiedAttribute) vytiahne z expectedoutput String , potom porovnat modfied a readatribute
            String readAttribute = JsonPath.parse(expectedOutput).set("$.plotly.host",modifiedAttribute).jsonString();
           String realOutput = JsonConfigApplication.readFileAsString(expectedOutput);




            if (!actualOutput.equals(expectedOutput)) {
                System.out.println("File edited");

            } else {
                System.out.println("Your file are equals");

            }
            //assertEquals(realOutput,readAttribute);
          assertEquals(expectedOutput,readAttribute);

        } catch (Exception e) {
            System.out.println("Your file doesnt equals" + e.getMessage());
            throw new RuntimeException(e);
       }

    }
}