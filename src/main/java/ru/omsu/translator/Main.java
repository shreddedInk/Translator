package ru.omsu.translator;

import java.io.FileReader;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {
//        FileReader reader = new FileReader(Paths.get("input1.txt").toFile());
//        PascalLexer lexer = new PascalLexer(reader);
//        Symbol token;
//        do {
//            token = lexer.next_token();
//        } while (token.sym != sym.EOF);

        FileReader reader1 = new FileReader(Paths.get("input1.txt").toFile());

        CustomSymbolFactory csf = new CustomSymbolFactory();
        PascalLexer lexer1 = new PascalLexer(reader1);
        Parser parser = new Parser(lexer1);
        parser.setSymbolFactory(csf);

        System.out.println("Начало разбора...");
        Symbol result = parser.parse();
        System.out.println("Результат разбора: " + result);

    }

//        Parser parser = new Parser();
//        parser.setScanner(lexer);
//        parser.setSymbolFactory(new CustomSymbolFactory());
//        parser.debug_parse(); //будет вывод Jasmin-кода на экран
}
