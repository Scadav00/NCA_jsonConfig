package com.example.JsonConfig;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.JsonConfig.JsonConfigApplication.readFileAsString;

@Controller
public class JsonController {
    @ResponseBody
    @RequestMapping(value = "/foos", method = RequestMethod.GET)
    public String getFoosBySimplePath() {
        return "Get some Foos";
    }

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String readJson() throws Exception {
        String file = "/home/ds/IdeaProjects/JsonConfig/src/main/java/com/example/JsonConfig/ConfigSimpleWithGson.json";
        String jsonConfig = readFileAsString(file);
        return jsonConfig;
    }

}
