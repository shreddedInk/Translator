package ru.omsu.translator.java;

import java.io.StringReader;
import java_cup.runtime.Symbol;

import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {
        String code = "if x = 10 then begin end";
        String code2 = "y= (x + 1)*3";
        String code3 = "ifSomething";

        PascalLexer lexer = new PascalLexer(new StringReader(code));
        Symbol token;



        while ((token = lexer.next_token()).sym != sym.EOF) {
            System.out.println("Token: " + token.sym + ", Value: " + token.value);

        }

    }
}