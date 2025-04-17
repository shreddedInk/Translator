package org.example.java;

import java_cup.runtime.Symbol;

public class CustomSymbol extends Symbol {
    private final Token token;
    private final Object attribute;  // Новое поле для хранения произвольного атрибута


    public Token getToken() {
        return token;
    }

    public CustomSymbol(int id, Token token, Object attribute) {
        super(id, token.getValue());
        this.token = token;
        this.attribute = attribute;  // Сохраняем атрибут
    }

    public CustomSymbol(int id, Symbol left, Symbol right, Token token, Object attribute) {
        super(id, left, right, token.getValue());
        this.token = token;
        this.attribute = attribute;
    }

    public Object getAttribute() {
        return attribute;  // Получаем атрибут
    }

    @Override
    public String toString() {
        return "CustomSymbol{" +
                "sym=" + sym +
                ", value=" + value +
                ", token=" + token +
                ", attribute=" + attribute +  // Выводим атрибут
                '}';
    }
}
