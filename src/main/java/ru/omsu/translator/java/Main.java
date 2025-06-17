package ru.omsu.translator.java;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.data.PascalLexer;
import ru.omsu.translator.emitter.Emitter;
import ru.omsu.translator.emitter.Formatter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader(new File("src/test/resources/Test6.txt"));
        CustomSymbolFactory csf = new CustomSymbolFactory();
        PascalLexer lexer1 = new PascalLexer(reader);


        Parser parser = new Parser(lexer1);
        FileWriter writer = new FileWriter("src/main/java/ru/omsu/translator/jasmin/Test.j");
        Emitter emitter= new Emitter(writer, new Formatter("Test",4));

        parser.setSymbolFactory(csf);
        parser.setEmitter(emitter);
        parser.parse();

        emitter.emit();
        writer.close();
    }
}