package org.example.java;

import java.util.*;

public class TypesControl {
    private static TypesControl instance;
    private Map<String, Node> graph;
/*
  {
  name:{Node1}
  s->p_to_s->

 */


    private static class Node{
        private String name;
        private Map<String, String> fields;
        private Node parent;
        public Node(String name,Node parent,Map<String,String> fields){
            this.name=name;
            this.fields = fields;
            this.parent = parent;
        }
        public Node(String name,Map<String,String> fields){
            this.name=name;
            this.fields = fields;
            this.parent = null;
        }
        public String getTypeName() {
            return name;
        }

        public Node getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return (parent != null ? "pointer to " + parent.toString() : name+fields.toString());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, fields, parent);
        }
    }
    private TypesControl(){
        this.graph = new HashMap<>();
        instance = this;
    }
    public static TypesControl getTypesControl(){
        if (instance==null){
            instance = new TypesControl();
        }
        return instance;
    }

    public void addNode(String name,String parent){
        Node parentNode = graph.getOrDefault(parent,null);
        HashMap<String,String> fields = new HashMap<>();
        fields.put("value",name);
        Node node = new Node(name,parentNode,fields);
        graph.putIfAbsent(name,node);
    }
    public void addNode(String name){
//        Node parentNode = graph.getOrDefault(parent,null);
        HashMap<String,String> fields = new HashMap<>();
        fields.put("value",name);
//        Node node = new Node(name,parentNode,fields);
        Node node = new Node(name,fields);
        graph.putIfAbsent(name,node);
    }
    public void addNode(String name,Map<String,String> field){
//        Node parentNode = graph.getOrDefault(parent,null);
//        HashMap<String,List<String>> fields = new HashMap<>();
//        fields.put("value",List.of(name));
//        Node node = new Node(name,parentNode,fields);
        Node node = new Node(name,field);
        graph.putIfAbsent(name,node);
    }
    public Node getNode(String name){
        return graph.get(name);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        for (var pair: graph.entrySet()){
            stringBuilder.append(pair.getKey());
            stringBuilder.append(":{\n");
//            stringBuilder.append(pair.getValue().toString());
//            stringBuilder.append()

                stringBuilder.append(pair.getValue().toString());
            stringBuilder.append("}\n");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}

