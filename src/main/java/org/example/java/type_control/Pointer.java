package org.example.java.type_control;

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
}
