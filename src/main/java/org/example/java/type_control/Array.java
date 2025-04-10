package org.example.java.type_control;

import java.util.Objects;

public class Array implements TypeI{
    private int start;
    private int end;
    private Pointer first;

    Array(int start, int end, TypeI type){
        this.start=start;
        this.end=end;
        first = type.pointer();
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
        Array array = (Array) o;
        return start == array.start && end == array.end && Objects.equals(first, array.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, first);
    }
}
