package ru.omsu.translator.java.type_control;

import java.util.HashMap;
import java.util.Map;

public class TypesTable{
    private static final Map<String, TypeExpression> typesTable = new HashMap<>();
    public Map<String, TypeExpression> TypesTable(){
        return typesTable;
    }



    public static TypeExpression getType(String id) {
        return typesTable.get(id);
    }

//    @Override
    public static void addType(String id, TypeExpression type) throws TypeException {
        TypeExpression t = typesTable.get(id);
        if (t==null){
            typesTable.put(id,type);
        }
        else if(t!=type){
            throw new TypeException(String.format("variable %s was already defined with type %. this definition has type %s",id,t,type));
        }
    }

    @Override
    public String toString() {
        return typesTable.toString();
    }
}
