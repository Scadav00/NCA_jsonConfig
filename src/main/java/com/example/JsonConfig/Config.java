package com.example.JsonConfig;

public class Config {
    private String host;
    private int port;
    private boolean debug;
    private String title;

    public ConfigPlotly(String host, int port, boolean debug, String title) {
        this.host = host;
        this.port = port;
        this.debug = debug;
        this.title = title;
    }

    class Boxes {
        public String name1;
        public String name2;
        public String name3;
        public String name4;

            private Boxes() {}

            public Boxes(String name1, String name2, String name3, String name4) {
                this.name1 = name1;
                this.name2 = name2;
                this.name3 = name3;
                this.name4 = name4;
            }
//    }


}

//    private class ConfigEs {
//        private String host;
//        private int port;
//
//        private class Srv {
//            private String index;
//            private String countQuery;
//
//            private Srv() {}
//
//            public Srv(String index, String countQuery) {
//                this.index = index;
//                this.countQuery = countQuery;
//            }
//        }
//
//        private class Gw{
//            private Gw() {}
//
//        }
//        private class Fw{
//            private Fw() {}
//        }
//    }
//
//    private class ConfigNas{
//        private String host;
//        private String port;
//        private String user;
//        private String password;
//
//        private class Storage{
//            private int limit_percentage;
//
//            private Storage() {}
//
//            public Storage(int limit_percentage) {
//                this.limit_percentage = limit_percentage;
//            }
//        }
//    }





