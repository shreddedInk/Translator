package ru.omsu.translator.java;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.data.PascalLexer;
import ru.omsu.translator.emitter.Emitter;
import ru.omsu.translator.emitter.Formatter;

import java.io.FileWriter;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        StringReader reader = new StringReader(
                """
                        var
                        x: integer;
                        begin
                        x := 5 + 10;
                        end.
                        """
                );

        CustomSymbolFactory csf = new CustomSymbolFactory();
        PascalLexer lexer1 = new PascalLexer(reader);
        Parser parser = new Parser(lexer1);
        FileWriter writer = new FileWriter("src/main/java/ru/omsu/translator/java/Test.j");
        Emitter emitter= new Emitter(writer, new Formatter("Test",4));
        parser.setSymbolFactory(csf);
        parser.setEmitter(emitter);
        System.out.println("Начало разбора...");
        Symbol result = parser.parse();
        System.out.println();
        System.out.println("Результат разбора: " + result);
        emitter.emit();
        writer.close();
        System.out.println(writer);
    }
}