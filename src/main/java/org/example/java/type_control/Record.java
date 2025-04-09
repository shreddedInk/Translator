package org.example.java.type_control;

import java.util.Map;

public class Record implements TypeI{
    String name;
    private Map<String,TypeI> args;
    Record(String name,String name1, TypeI type1){
        this.name = name;
        args.put(name1,type1);

    }
    Record(String name,String name1, TypeI type1,String name2, TypeI type2){
        this.name=name;
        args.put(name1,type1);
        args.put(name2,type2);
    }
    Record(String name,String name1, TypeI type1,String name2, TypeI type2,String name3, TypeI type3){
        this.name=name;
        args.put(name1,type1);
        args.put(name2,type2);
        args.put(name3,type3);
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
