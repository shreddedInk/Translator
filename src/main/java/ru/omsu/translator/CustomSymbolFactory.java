package ru.omsu.translator;

import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

public class CustomSymbolFactory implements SymbolFactory {

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        System.out.println("CustomSymbolFactory.newSymbol: " + name);
        Token token = value instanceof Token
                ? (Token) value
                : new Token(id, value);
        return new CustomSymbol(id, left, right, token);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        System.out.println("CustomSymbolFactory.newSymbol: " + name);
        return new CustomSymbol(id, left, right, new Token(id, null));
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Object value) {
        System.out.println("CustomSymbolFactory.newSymbol: " + name);
        Token token = value instanceof Token
                ? (Token) value
                : new Token(id, value);
        return new CustomSymbol(id, left, null, token);
    }

    @Override
    public Symbol newSymbol(String name, int id, Object value) {
        System.out.println("CustomSymbolFactory.newSymbol: " + name);
        Token token = value instanceof Token
                ? (Token) value
                : new Token(id, value);
        return new CustomSymbol(id, token);
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        System.out.println("CustomSymbolFactory.newSymbol: " + name);
        return new CustomSymbol(id, new Token(id, null));
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        System.out.println("CustomSymbolFactory.startSymbol: " + name);
        CustomSymbol symbol = new CustomSymbol(id, new Token(id, null));
        symbol.parse_state = state;
        return symbol;
    }
}
