package com.cloudgy.check.config;

/**
 * Created by panyi on 2016/11/7.
 */
public class ServerConfig {
    public static Environment environment = Environment.TEST;
    public enum Environment{
        TEST,
        REL
    }

    public static final String t_host="http://192.168.1.1:8080/";
    public static final String r_host="http://www.baidu.com/";

    public static String getHost(){
        return environment == Environment.REL?r_host:t_host;
    }

}//end class
