package ru.omsu.translator.java;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private static Map<String, Object> variables = new HashMap<>();


    public static void setVariable(String name, Object value) {
        variables.put(name, value);
    }


    public static Integer getIntVariable(String name) {
        Object value = variables.get(name);
        return (value instanceof Integer) ? (Integer) value : 0;
    }

    public static Boolean getBoolVariable(String name) {
        Object value = variables.get(name);
        return (value instanceof Boolean) ? (Boolean) value : false;
    }


    public static void addVariable(String name, Object value) {
        variables.putIfAbsent(name, value);
    }
}