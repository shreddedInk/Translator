package org.example.java;

import java_cup.runtime.Symbol;

import java.util.HashMap;
import java.util.Map;

public class CustomSymbol extends Symbol {
    private final Token token; // Новое поле для хранения произвольного атрибута
    private final Map<String, Object> attributes;


    public Token getToken() {
        return token;
    }

    public CustomSymbol(int id, Token token) {
        super(id, token.getValue());
        this.token = token;
        this.attributes = new HashMap<>();  // Инициализируем пустую карту атрибутов
    }

    public CustomSymbol(int id, Symbol left, Symbol right, Token token) {
        super(id, left, right, token.getValue());
        this.token = token;
        this.attributes = new HashMap<>();  // Инициализируем пустую карту атрибутов
    }

    // Добавление атрибута
    public void addAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }


    // Получение атрибута по имени
    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public String toString() {
        return "CustomSymbol{" +
                "sym=" + sym +
                ", value=" + value +
                ", token=" + token +
                ", attributes=" + attributes +  // Выводим все атрибуты
                '}';
    }
}
