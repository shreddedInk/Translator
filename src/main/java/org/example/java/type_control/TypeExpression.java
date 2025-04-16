package org.example.java.type_control;

import java.util.Map;
import java.util.Objects;

public class TypeExpression {
    private Node head;
    public class Node{
        private Node parent;
        private final String name;
        private Integer start;
        private Integer end;
        private Map<String,Node> params;


        public Node(String name) {
            this.name = name;
            this.parent=null;
            this.start = this.end = null;
            this.params = null;
        }

        public Node(Node parent) {
            this.parent = parent;
            this.name = "pointer to "+parent.name;
            this.start = this.end = null;
            this.params = null;
        }

        public Node(Node parent, Integer start, Integer end) {
            this.parent = parent;
            this.name = "array of "+parent.name;
            this.start = start;
            this.end = end;
        }

        public Node(Map<String, Node> params) {
            this.name = "record";
            this.params = params;
        }

        public Node getParent() {
            return parent;
        }

        public String getName() {
            return name;
        }

        public Integer getStart() {
            return start;
        }

        public Integer getEnd() {
            return end;
        }

        public Map<String, Node> getParams() {
            return params;
        }

        @Override
        public String toString() {
            if (params!=null){
                StringBuilder stringBuilder = new StringBuilder("record{");
                for (var pair: params.entrySet()){
                    stringBuilder.append(pair.getKey());
                    stringBuilder.append(":");
                    stringBuilder.append(pair.getValue());
                    stringBuilder.append(", " );
                }
                return stringBuilder.toString();
            }
            else if(start!=null){
                return "array of "+parent+"["+start+"..."+end+"]";
            }
            else if (parent!=null){
                return "*"+parent;
            }
            else return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(parent, node.parent) && Objects.equals(name, node.name) && Objects.equals(start, node.start) && Objects.equals(end, node.end) && Objects.equals(params, node.params);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent, name, start, end, params);
        }
    }

    private Node integer(){
        return new Node("integer");
    }
    private Node real(){
        return new Node("real");
    }

    private Node pointerTo(Node name){
        return new Node(name);
    }

    private Node array(int start, int end, Node type){
            return new Node(type,start,end);
    }

    private Node isPointer(Node node){
        return node.getParent();
    }

}
