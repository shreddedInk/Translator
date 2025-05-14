package ru.omsu.translator;

import java.io.FileReader;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("D:/транслятор/Translator/input.txt");
        PascalLexer lexer = new PascalLexer(reader);
        Symbol token;
        do {
            token = lexer.next_token();
            System.out.println("Token: " + token.sym + " (" + sym.terminalNames[token.sym] + ") = " + token.value);
        } while (token.sym != sym.EOF);

        lexer = new PascalLexer(new FileReader("D:/транслятор/Translator/input.txt")); // Перезагружаем лексер
        Parser parser = new Parser();
        parser.setScanner(lexer);
        parser.setSymbolFactory(new CustomSymbolFactory());
        try {
            parser.debug_parse();
        } catch (Exception e) {
            System.err.println("Parser error: " + e.getMessage());
            e.printStackTrace();
        }
    }

//        Parser parser = new Parser();
//        parser.setScanner(lexer);
//        parser.setSymbolFactory(new CustomSymbolFactory());
//        parser.debug_parse(); //будет вывод Jasmin-кода на экран
    }
