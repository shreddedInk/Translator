package ru.omsu.translator.java.type_control;

public class ArrayTypeExpression extends TypeExpression{

//    private Node head;
    private int start;
    private int end;
    public ArrayTypeExpression(TypeExpression.Node head,int start,int end) {
        super(head);
        this.start=start;
        this.end=end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
