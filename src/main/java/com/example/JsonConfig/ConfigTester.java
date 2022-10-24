package com.example.JsonConfig;


import com.example.JsonConfig.Plotly;
import com.google.gson.Gson;

public class ConfigTester {
    public static void main(String[] args) throws Exception {
        System.out.println("welcome to gson");

//        serializeConfigSimple();
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
        Plotly configSimple= gson.fromJson(configJson, Plotly.class);

    }

}
