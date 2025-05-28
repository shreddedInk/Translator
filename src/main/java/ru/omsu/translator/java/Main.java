package ru.omsu.translator.java;

import java.io.StringReader;
import java.io.StringWriter;

import java_cup.runtime.Symbol;
import ru.omsu.translator.data.PascalLexer;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.emitter.Emitter;
import ru.omsu.translator.emitter.Formatter;

public class Main {
    public static void main(String[] args) throws Exception {
        StringReader reader = new StringReader("begin x := true or false end");

        CustomSymbolFactory csf = new CustomSymbolFactory();
        PascalLexer lexer1 = new PascalLexer(reader);
        Parser parser = new Parser(lexer1);
        StringWriter writer = new StringWriter();
        Emitter emitter= new Emitter(writer, new Formatter("Test",4));
        parser.setSymbolFactory(csf);
        parser.setEmitter(emitter);
        System.out.println("Начало разбора...");
        Symbol result = parser.parse();
        System.out.println();
        System.out.println("Результат разбора: " + result);
        System.out.println(emitter.getMethods());
    }
}