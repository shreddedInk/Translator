package org.example.java;

import java_cup.runtime.Symbol;

public class CustomSymbol extends Symbol {
    private final Token token;

    public CustomSymbol(int id, Token token) {
        super(id, token.getValue());
        this.token = token;
    }

    public CustomSymbol(int id, Symbol left, Symbol right, Token token) {
        super(id, left, right, token.getValue());
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "CustomSymbol{" +
                "sym=" + sym +
                ", value=" + value +
                ", token=" + token +
                '}';
    }
}
