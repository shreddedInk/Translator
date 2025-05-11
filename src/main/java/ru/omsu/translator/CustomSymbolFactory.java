package ru.omsu.translator;

import java_cup.runtime.SymbolFactory;
import java_cup.runtime.Symbol;

public class CustomSymbolFactory implements SymbolFactory {

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        if (value instanceof Token) {
            Token token = (Token) value;
            return new CustomSymbol(id, left, right, token); // Передаем правый символ и значение токена
        }
        return new CustomSymbol(id, left, right, new Token(id, value)); // Если value не является токеном, создаем стандартный Symbol
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return new Symbol(id, left, right); // Создаем Symbol без значения
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Object value) {
        if (value instanceof Token) {
            Token token = (Token) value;
            return new CustomSymbol(id, left, null, token); // Используем только left и token, если right нет
        }
        return new Symbol(id, left, null, value); // Создаем Symbol с null для right
    }

    @Override
    public Symbol newSymbol(String name, int id, Object value) {
        if (value instanceof Token) {
            Token token = (Token) value;
            return new CustomSymbol(id, null, null, token); // Только токен, без left и right
        }
        return new Symbol(id, value); // Стандартный Symbol для других значений
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        return new Symbol(id); // Создаем Symbol только с id
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        return new Symbol(id, state); // Для начального состояния возвращаем Symbol
    }
}
