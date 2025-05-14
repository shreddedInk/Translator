package ru.omsu.translator.data;
import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;
import ru.omsu.translator.Token;
import ru.omsu.translator.CustomSymbol;
import ru.omsu.translator.cup.sym;
%%

%class PascalLexer
%unicode
%cup
%line
%column
%public

%state COMMENT

%{
    private Symbol symbol(int type) {
        Symbol symbol = new CustomSymbol(type, new Token(type, yytext()));
        System.out.println("Создан токен по тайпу(Jflex): " + type + " со значанием дефолтным: " + yytext());
        if (type < 0) {
            throw new RuntimeException("Unexpected symbol type = -1 for text: " + yytext());
        }
        return symbol;
    }

    private Symbol symbol(int type, Object value) {
        Symbol symbol = new CustomSymbol(type, new Token(type, value));
        System.out.println("Создан токен по тайпу и по значению(Jflex): " + type + " Значение: " + value);
        if (type < 0) {
            throw new RuntimeException("Unexpected symbol type = -1 for text: " + yytext());
        }
        return symbol;
    }

     public void initialize() {
            yyreset(new java.io.StringReader(""));
    }
%}
KEYWORDS = ("begin" | "end")


%%

<YYINITIAL> {
    {KEYWORDS}   {    if (yytext().equals("begin")) return symbol(sym.BEGIN, yytext());
                      if (yytext().equals("end")) return symbol(sym.END, yytext());
      }
}