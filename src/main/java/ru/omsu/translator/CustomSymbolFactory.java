package ru.omsu.translator;

import java_cup.runtime.SymbolFactory;
import java_cup.runtime.Symbol;

public class CustomSymbolFactory implements SymbolFactory {

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        return new Symbol(id, left, right, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return new Symbol(id, left, right);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Object value) {
        return new Symbol(id, left, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Object value) {
        return new Symbol(id, value);
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        return new Symbol(id);
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        Symbol symbol = new Symbol(id);
        symbol.parse_state = state;
        return symbol;
    }
}