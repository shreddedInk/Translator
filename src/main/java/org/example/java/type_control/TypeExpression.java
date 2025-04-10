package org.example.java.type_control;

public class TypeExpression {
    private class Node{
        private Node parent;
        private TypeI type;

        Node(Node parent, TypeI type){
            this.parent=parent;
            this.type=type;
        }
    }

    public static TypeI createPrimitiveType(String name){
        return new Primitive(name);
//        Node node = new Node(null,p);
    }

    public static TypeI array(int start,int end,TypeI type){
        return type.array(start,end);
    }

    public static TypeI pointerTo(TypeI type){
        return type.pointer();
    }

    public static TypeI record(String name, String paramName1, TypeI paramType1){
        return new Record(name,paramName1,paramType1);
    }
    public static TypeI record(String name, String paramName1, TypeI paramType1,
                               String paramName2, TypeI paramType2){
        return new Record(name,paramName1,paramType1,paramName2,paramType2);

    }public static TypeI record(String name, String paramName1, TypeI paramType1,
                                String paramName2, TypeI paramType2,
                                String paramName3, TypeI paramType3){
        return new Record(name,paramName1, paramType1,
                paramName2, paramType2,
                paramName3, paramType3);
    }

}
