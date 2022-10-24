package com.example.JsonConfig;

public class Plotly {
    private String host;
    private int port;
    private boolean debug;
    private String title;

    public Plotly(String host, int port, boolean debug, String title) {
        this.host = host;
        this.port = port;
        this.debug = debug;
        this.title = title;
    }

}
