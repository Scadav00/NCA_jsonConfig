package com.example.JsonConfig;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonConfigApplicationTest {


    public final String file1 = "/home/fo/IdeaProjects/NCA_jsonConfig/src/main/java/com/example/JsonConfig/ConfigSimpleWithGson.json";




    @Test
    void mapSerialization() {
        try{
            JsonConfigApplication jsonConfigApplication = new JsonConfigApplication();
            byte[] actualOutput = jsonConfigApplication.mapSerialization();
            Map<String, Object> configMap = jsonConfigApplication.mapJson();
            byte[] expectedOutput = SerializationUtils.serialize((Serializable) configMap);// tu som musel pridat serializable

            assertArrayEquals(actualOutput,expectedOutput);

        } catch (IOException e) {
            System.out.println("Your file cant be serialize "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Test
    void mapDeserialization() {
        try{
            JsonConfigApplication jsonConfigApplication = new JsonConfigApplication();
            Object actualOutput =jsonConfigApplication.mapDeserialization();
            Object exceptedOutput = jsonConfigApplication.mapJson();


            assertEquals(exceptedOutput, actualOutput);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @Test
    void mapJson() {
        try {
            JsonConfigApplication jsonConfigApplication = new JsonConfigApplication();

            Map actualOutput =  jsonConfigApplication.mapJson(); // z kade vedelo ktori file chcem porovnavat ?
            String loc= "/home/fo/IdeaProjects/NCA_jsonConfig/src/main/java/com/example/JsonConfig/Config.json";
            String jsonConfig = jsonConfigApplication.readFileAsString(loc); //tu som musel pridat jsonConfigapplication
            Map expectedOutput = new Gson().fromJson(jsonConfig, Map.class);

           // new Gson().fromJson(jsonConfig, Map.class);

//
//            System.out.println(actualOutput);
//            System.out.println(expectedOutput);
            assertEquals(actualOutput,expectedOutput); // zly asserEquls ????
           // assertTrue(actualOutput.equals(expectedOutput))

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void readFileAsString() {
        try {
            JsonConfigApplication jsonConfigApplication= new JsonConfigApplication();
            String actualOutput = jsonConfigApplication.readFileAsString(file1);
            String expectedOutput = (Files.readString(Paths.get(file1)));

            assertEquals(expectedOutput, actualOutput);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void editJson() {

        try{
            JsonConfigApplication jsonConfigApplication = new JsonConfigApplication();
            String json = new String(Files.readAllBytes(Paths.get(file1)));
            String actualOutput = jsonConfigApplication.readFileAsString(file1);
            String expectedOutput =  JsonPath.parse(json).set("$.plotly.host", "ffffff").jsonString();

            System.out.println(actualOutput);
            System.out.println(expectedOutput);

            assertNotEquals(actualOutput,expectedOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}