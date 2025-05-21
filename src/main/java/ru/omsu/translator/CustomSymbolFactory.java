package ru.omsu.translator;

import java_cup.runtime.SymbolFactory;
import java_cup.runtime.Symbol;

public class CustomSymbolFactory implements SymbolFactory {

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        if (value instanceof Token && left != null && right != null) {
            return new CustomSymbol(id, left, right, (Token) value);
        }
        return new Symbol(id, left, right, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        // Если нужно, можно возвращать CustomSymbol, но без value это редко нужно
        return new Symbol(id, left, right);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Object value) {
        if (value instanceof Token && left != null) {
            return new CustomSymbol(id, left, null, (Token) value);
        }
        return new Symbol(id, left, null, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Object value) {
        if (value instanceof Token) {
            return new CustomSymbol(id, null, null, (Token) value);
        }
        return new Symbol(id, value);
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        return new Symbol(id);
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        return new Symbol(id, state);
    }
}