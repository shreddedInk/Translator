package ru.omsu.translator.data;
import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;
import ru.omsu.translator.java.Token;
import ru.omsu.translator.java.CustomSymbol;
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
            Token token = new Token(type, yytext());
            if (type < 0) {
                throw new RuntimeException("Вывело -1 для этого: " + yytext());
            }
            return new CustomSymbol(type, token);
        }

        private Symbol symbol(int type, Object value) {
            Token token = new Token(type, value);
            if (type < 0) {
                throw new RuntimeException("Вывело -1 для этого: " + yytext());
            }
            return new CustomSymbol(type, token);
        }

        public void initialize() {
            yyreset(new java.io.StringReader(""));
        }
%}
IDENTIFIER = [a-zA-Z_][a-zA-Z_0-9]*
NUMBER = [0-9]+
STRING = \'([^\\']|\\.)*\'
CHAR = \'([^\\]|\\.)\'
WHITESPACE = [ \t\r\n]+

KEYWORDS = ("if"|"while"|"for"|"array"|"function")
BOOLEAN_LITERAL = ("true"|"false")
ASSIGN = ":="
PLUS = "+"
MINUS = "-"
OR = "or"
STAR = "*"
DIV = "/"
AND = "and"
NOT = "not"
COMPARISON = ("="|"<>"|"<"|">"|"<="|">=")

LPAREN = "("
RPAREN = ")"
LBRACKET = "["
RBRACKET = "]"
BEGIN = "begin"
END = "end"
WRITE = "write"

%%

<YYINITIAL> {
    {BOOLEAN_LITERAL}       {
          System.out.println("создан токен (Jflex): " + symbol(sym.BOOLEAN_LITERAL, yytext()));
          return symbol(sym.BOOLEAN_LITERAL, yytext()); }

    {KEYWORDS}      {
          System.out.println("создан токен (Jflex): " + symbol(sym.KEYWORD, yytext()));
          return symbol(sym.KEYWORD, yytext());}

    {NUMBER}        {
          System.out.println("создан токен (Jflex): "+symbol(sym.NUMBER, Integer.parseInt(yytext())));
          return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {CHAR}          {
          System.out.println("создан такой токен (Jflex): "+symbol(sym.CHAR, yytext().charAt(1)));
          return symbol(sym.CHAR, yytext().charAt(1));
      }
    {PLUS} {
          System.out.println("создан токен (Jflex): " + symbol(sym.PLUS, yytext()));
          return symbol(sym.PLUS, yytext());
      }
    {MINUS} {
          System.out.println("создан токен (Jflex): " + symbol(sym.MINUS, yytext()));
          return symbol(sym.MINUS, yytext());
        }
    {OR} {
          System.out.println("создан токен (Jflex): " + symbol(sym.OR, yytext()));
          return symbol(sym.OR, yytext());
      }
    {STAR} {
          System.out.println("создан токен (Jflex): " + symbol(sym.STAR, yytext()));
          return symbol(sym.STAR, yytext());
      }
    {DIV} {
          System.out.println("создан токен (Jflex): " + symbol(sym.DIV, yytext()));
          return symbol(sym.DIV, yytext());
      }
    {AND} {
          System.out.println("создан токен (Jflex): " + symbol(sym.AND, yytext()));
          return symbol(sym.AND, yytext());
      }

    {NOT}           {
          System.out.println("создан токен (Jflex): " + symbol(sym.NOT, yytext()));
          return symbol(sym.NOT, yytext());
      }
    {ASSIGN}        {
          System.out.println("создан токен (Jflex): " + symbol(sym.ASSIGN, yytext()));
          return symbol(sym.ASSIGN, yytext());
       }
    {COMPARISON}    {
        switch(yytext()) {
            case "=":  return symbol(sym.EQ, yytext());
            case "<>": return symbol(sym.NEQ, yytext());
            case "<":  return symbol(sym.LT, yytext());
            case ">":  return symbol(sym.GT, yytext());
            case "<=": return symbol(sym.LEQ, yytext());
            case ">=": return symbol(sym.GEQ, yytext());
        }
    }

    {LPAREN}        {
          System.out.println("создан токен (Jflex): " + symbol(sym.LPAREN, yytext()));
          return symbol(sym.LPAREN, yytext());
       }
    {RPAREN}        {
          System.out.println("создан токен (Jflex): " + symbol(sym.RPAREN, yytext()));
          return symbol(sym.RPAREN, yytext());
      }
    {LBRACKET}      {
          System.out.println("создан токен (Jflex): " + symbol(sym.LBRACKET, yytext()));
          return symbol(sym.LBRACKET, yytext());
       }
    {RBRACKET}      {
          System.out.println("создан токен (Jflex): " + symbol(sym.RBRACKET, yytext()));
          return symbol(sym.RBRACKET, yytext());
       }
    {BEGIN}         {
          System.out.println("создан токен (Jflex): " + symbol(sym.BEGIN, yytext()));
          return symbol(sym.BEGIN, yytext());
       }
    {END}           {
          System.out.println("создан токен (Jflex): " + symbol(sym.END, yytext()));
          return symbol(sym.END, yytext());
       }
    {WRITE}         {
          System.out.println("создан токен (Jflex): " + symbol(sym.WRITE, yytext()));
          return symbol(sym.WRITE, yytext());
      }
    {IDENTIFIER} {
          System.out.println("создан токен (Jflex): "+symbol(sym.IDENTIFIER, yytext()));
          return symbol(sym.IDENTIFIER, yytext()); }
    {STRING}        {
              System.out.println("создан токен (Jflex): "+symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)));
              return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1));
        }

    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { System.out.println("Проигнорирован пробел"); /* Пропускаем пробелы */ }
    [ \t]+       { System.out.println("Проигнорирована табулиция");  /* Игнорировать пробелы и табуляцию */ }
    [\n]+        { System.out.println("Проигнорирован перенос строки");/* Игнорировать переносы строк */ }
    <<EOF>> { System.out.println("создан такой токен(Jflex): " + symbol(sym.EOF, null));
          return symbol(sym.EOF, null); }
}
<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}