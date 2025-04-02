//package org.example.java;
//
//import java.util.Map;
//
//class Node{
//    private String name;
//    private Map<String, Node> fields;
//    private Node parent;
//    public Node(String name, Map<String, Node> fields, Node parent){
//        this.name=name;
//        this.fields = fields;
//        this.parent = parent;
//    }
//    public Node(String name,Map<String, Node> fields){
//        this.name=name;
//        this.fields = fields;
//        this.parent = null;
//    }
//    public String getTypeName() {
//        return name;
//    }
//
//    public Node getParent() {
//        return parent;
//    }
//
//    @Override
//    public String toString() {
//        return (parent != null ? "pointer to " + parent.toString() : name);
//    }
//}
