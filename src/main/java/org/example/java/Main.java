package org.example.java;

import java.io.StringReader;
import java_cup.runtime.Symbol;
import org.example.data.PascalLexer;

public class Main {
    public static void main(String[] args) throws Exception {
        String code = "if x = 10 then begin end";
        String code2 = "y= (x + 1)*3";
        String code3 = "ifSomething";

        PascalLexer lexer = new PascalLexer(new StringReader(code));
        Symbol token;

        while ((token = lexer.next_token()).sym != PascalLexer.sym.EOF) {
            System.out.println("Token: " + token.sym + ", Value: " + token.value);
        }

    }
}