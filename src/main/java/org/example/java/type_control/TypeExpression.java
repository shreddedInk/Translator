package org.example.java.type_control;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TypeExpression {
    private Node head;
    private class Node{
        private final Node child;
        private final Type type;

        private Node(Type type,Node child) {
            this.type = type;
            this.child = child;
        }
//        private Map<String,Node> params;


//        public Node(Type type) {
//            this.type = type;
//            this.child=null;
//            this.start = this.end = null;
//            this.params = null;
//        }
//
//        public Node(Node child) {
//            this.child = child;
//            this.type = Type.POINTER;
//            this.start = this.end = null;
//            this.params = null;
//        }
//
//        public Node(Node child, Integer start, Integer end) {
//            this.child = child;
//            this.type = Type.ARRAY;
//            this.start = start;
//            this.end = end;
//        }
//
//        public Node(Map<String, Node> params) {
//            this.type = Type.RECORD;
//            this.params = params;
//        }

        public Node getChild() {
            return child;
        }

        public String getName() {
            return type.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(child, node.child) && type == node.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(child, type);
        }
    }
    private class ArrayNode extends Node{
        private final Integer start;
        private final Integer end;
        public ArrayNode(Node child, Integer start, Integer end) {
            super(Type.ARRAY,child);
            this.start = start;
            this.end = end;
        }
        public Integer getStart() {
            return start;
        }

        public Integer getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayNode arrayNode = (ArrayNode) o;
            return Objects.equals(start, arrayNode.start) && Objects.equals(end, arrayNode.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
    private class RecordNode extends Node{
        private Map<String,Node> params;

        private RecordNode(Map<String,Node> params) {
            super(Type.RECORD, null);
            this.params=params;
        }
        public Map<String, Node> getParams() {
            return params;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecordNode that = (RecordNode) o;
            return Objects.equals(params, that.params);
        }

        @Override
        public int hashCode() {
            return Objects.hash(params);
        }
    }
    private class PointerNode extends Node{
        private PointerNode(Node child) {
            super(Type.POINTER, child);
        }
    }

    public TypeExpression(Node head){
        this.head = head;
    }

    private Node integerNode(){
        return new Node(Type.INTEGER,null);
    }
    private Node realNode(){
        return new Node(Type.REAL,null);
    }

    private Node pointerToNode(Node name){
        return new PointerNode(name);
    }

    private Node getHead() {
        return head;
    }

    private Node arrayNode(int start, int end, Node type){
            return new ArrayNode(type,start,end);
    }

    private Node isPointerNode(Node node){
        return node.getChild();
    }

    public TypeExpression integer(){
        Node integer = new Node(Type.INTEGER,null);
        return new TypeExpression(integer);
    }

    public TypeExpression real(){
        Node real = new Node(Type.REAL,null);
        return new TypeExpression(real);
    }

    public TypeExpression array(int start, int end, TypeExpression type){
        return new TypeExpression(new ArrayNode(type.getHead(),start,end));
    }
    public TypeExpression pointerTo(TypeExpression type){
        return new TypeExpression(new PointerNode(type.getHead()));
    }

    public TypeExpression record(Map<String,TypeExpression> params){
        Map<String,Node> stringNodeMap = params.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().getHead()));
        return new TypeExpression(new RecordNode(stringNodeMap));
    }


}
