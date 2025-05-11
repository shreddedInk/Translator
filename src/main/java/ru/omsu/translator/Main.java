package ru.omsu.translator;

import java.io.FileReader;

import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("D:/транслятор/Translator/input.txt");
        PascalLexer lexer = new PascalLexer(reader);
        Parser parser = new Parser();
        parser.setScanner(lexer);
        parser.setSymbolFactory(new CustomSymbolFactory());
        parser.parse(); //будет вывод Jasmin-кода на экран
    }
}