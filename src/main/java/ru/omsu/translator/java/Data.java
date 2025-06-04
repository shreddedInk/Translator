package ru.omsu.translator.java;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private static Map<String, Integer> variables = new HashMap<>();

    public Map<String, Integer> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Integer> variables) {
        this.variables = variables;
    }


    public void addVariable(String name, Integer value){
        variables.putIfAbsent(name, value);
    }

    public static Integer getVariable(String name){
        return variables.getOrDefault(name, 0);
    }
    public static void setVariable(String name, Integer value) {
        variables.put(name, value);
    }

    @Override
    public String toString() {
        return variables.toString();
    }
}
