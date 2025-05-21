package ru.omsu.translator;

import java_cup.runtime.SymbolFactory;
import java_cup.runtime.Symbol;

public class CustomSymbolFactory implements SymbolFactory {
    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        if (value instanceof Token) {
            return new CustomSymbol(id, left, right, (Token) value);
        }
        // Если value не Token, создаём обычный Symbol (или можно выбросить исключение)
        return new Symbol(id, left, right, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        // Если нет Token, создаём Symbol без значения
        return new Symbol(id, left, right);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Object value) {
        if (value instanceof Token) {
            return new CustomSymbol(id, left, null, (Token) value); // right = null
        }
        return new Symbol(id, left, value);
    }

    @Override
    public Symbol newSymbol(String name, int id, Object value) {
        if (value instanceof Token) {
            return new CustomSymbol(id, (Token) value);
        }
        return new Symbol(id, value);
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        return new Symbol(id); // или можно вернуть CustomSymbol с Token = null
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        Symbol symbol = new Symbol(id);
        symbol.parse_state = state;
        return symbol;
    }
}