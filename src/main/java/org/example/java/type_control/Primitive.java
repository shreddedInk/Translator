package org.example.java.type_control;

import java.util.Objects;

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

    @Override
    public boolean isPointer() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Primitive primitive = (Primitive) o;
        return Objects.equals(name, primitive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
