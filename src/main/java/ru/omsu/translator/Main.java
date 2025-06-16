package ru.omsu.translator;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {

        FileReader reader = new FileReader(Paths.get("input1.txt").toFile());

        CustomSymbolFactory csf = new CustomSymbolFactory();
        PascalLexer lexer = new PascalLexer(reader);

        Parser parser = new Parser(lexer, csf);
        parser.setScanner(lexer);

        Field scannerField = parser.getClass().getSuperclass().getDeclaredField("_scanner");
        scannerField.setAccessible(true);
        scannerField.set(parser, lexer);

        parser.parse();

    }

//        Parser parser = new Parser();
//        parser.setScanner(lexer);
//        parser.setSymbolFactory(new CustomSymbolFactory());
//        parser.debug_parse(); //будет вывод Jasmin-кода на экран
}
