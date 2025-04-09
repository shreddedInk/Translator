package org.example.java.type_control;

public class Primitive implements TypeI{


    String name;

    Primitive(String name){
        this.name=name;
    }
    @Override
    public Pointer pointer() {
        return new Pointer(this);
    }

    @Override
    public Array array(int start, int end) {
        return new Array(start,end,this);
    }
}
