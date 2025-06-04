package ru.omsu.translator;


import ru.omsu.translator.cup.sym;

import java.util.Objects;


public class Token {
    public static final int NUMBER = sym.NUMBER;
    public static final int IDENTIFIER = sym.IDENTIFIER;
    private final int type;
    private final Object value;


    public Token(int type, Object value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value=" + value + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}