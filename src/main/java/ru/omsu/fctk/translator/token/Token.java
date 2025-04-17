package ru.omsu.fctk.translator.token;

public class Token {
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
}