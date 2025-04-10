package org.example.java.type_control;

import java.util.Objects;

public class Pointer implements TypeI{
    private TypeI parent;
    Pointer(TypeI parent){
        this.parent = parent;
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
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pointer pointer = (Pointer) o;
        return Objects.equals(parent, pointer.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent);
    }
}
