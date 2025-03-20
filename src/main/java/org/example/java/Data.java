package org.example.java;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String, Object> variables = new HashMap<>();

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public void addVariable(String name, Object value){
        variables.putIfAbsent(name, value);
    }

    public  Object getVariable(String name){
        return variables.getOrDefault(name, 0);
    }
}
