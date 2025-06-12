package ru.omsu.translator.java.type_control;

public interface ITypesTable {
    TypeExpression getType(String id);
    void addType(String id, TypeExpression type) throws TypeException;
}
