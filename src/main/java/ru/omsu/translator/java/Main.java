package ru.omsu.translator.java;

import java_cup.runtime.Symbol;
import ru.omsu.translator.cup.Parser;
import ru.omsu.translator.data.PascalLexer;
import ru.omsu.translator.emitter.Emitter;
import ru.omsu.translator.emitter.Formatter;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {


                String code = "begin x := true or false end";
                String code2 = "begin y:= (4 + 1)*3 end";
                String code3 = "ifSomething";
                String code4 ="var x : integer; y : real begin x:= 5 + 10 end";

                PascalLexer lexer = new PascalLexer(new StringReader(code4));
                Symbol token;
//                while ((token = lexer.next_token()).sym != sym.EOF) {
//                    System.out.println("Token: " + token + ", Value: " + token.value);
//
//                }
                Parser parser = new Parser();
//                StringWriter jasmin = new StringWriter();
                parser.setScanner(lexer);
                parser.setSymbolFactory(new CustomSymbolFactory());
                parser.setEmitter(new Emitter(new StringWriter(),new Formatter("Main",1)));
                try {
                    Symbol result = parser.parse();
                    System.out.println("Результат разбора: " + result);
                    System.out.println(new TypesTable());
                }
                catch (Exception e){
                    System.out.println(new TypesTable());
                    throw e;
                }
            }
        }
