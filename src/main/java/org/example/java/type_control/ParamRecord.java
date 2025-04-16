package org.example.java.type_control;

public class ParamRecord<T> {
    private String name;
    private T value;

    public ParamRecord(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }
}
