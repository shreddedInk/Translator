package org.example.java.type_control;

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
}
