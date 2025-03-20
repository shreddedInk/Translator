package org.example.java;

import org.example.data.PascalLexer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader reader = new InputStreamReader(System.in);
        PascalLexer lexer = new PascalLexer(reader);
        Parser parser = new Parser(lexer);
        parser.parse();
    }
}
