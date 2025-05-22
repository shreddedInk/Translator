package ru.omsu.translator.java;

import java.io.StringReader;
import java.io.StringWriter;

import java_cup.runtime.Symbol;

import java_cup.runtime.SymbolFactory;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.emitter.Emitter;
import ru.omsu.translator.emitter.Formatter;

public class Main {
    public static void main(String[] args) throws Exception {
        String code = "x := true or false";
        String code2 = "y= (x + 1)*3";
        String code3 = "ifSomething";

        PascalLexer lexer = new PascalLexer(new StringReader(code));
        Symbol token;
        while ((token = lexer.next_token()).sym != sym.EOF) {
            System.out.println("Token: " + token.sym + ", Value: " + token.value);

        }
        Parser parser = new Parser(lexer);
        StringWriter jasmin = new StringWriter();
        parser.setSymbolFactory(new CustomSymbolFactory());
        Symbol result = parser.parse();
        System.out.println("Результат разбора: " + result);
    }
}