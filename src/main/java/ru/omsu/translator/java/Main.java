package ru.omsu.translator.java;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.cup.sym;
import ru.omsu.translator.data.PascalLexer;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {


                String code = "x := true or false";
                String code2 = "y= (x + 1)*3";
                String code3 = "ifSomething";
                String code4 ="var x : integer begin x:=5 end.";

                PascalLexer lexer = new PascalLexer(new StringReader(code4));
                Symbol token;
                while ((token = lexer.next_token()).sym != sym.EOF) {
                    System.out.println("Token: " + token.sym + ", Value: " + token.value);

                }
                Parser parser = new Parser(lexer,new CustomSymbolFactory());
//                StringWriter jasmin = new StringWriter();
//                parser.setSymbo\lFactory();
                Symbol result = parser.parse();
                System.out.println("Результат разбора: " + result);
            }
        }
