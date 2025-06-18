package ru.omsu.translator.java.type_control;

import ru.omsu.translator.java.type_control.TypeExpression;

public interface ITypesTable {
    TypeExpression getType(String id);
    void addType(String id, TypeExpression type) throws TypeException;
}
