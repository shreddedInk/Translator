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

IDENTIFIER = [a-z][a-zA-Z_0-9]*
NUMBER = [0-9]+
STRING = \'([^\\']|\\.)*\'
CHAR = \'([^\\]|\\.)\'
WHITESPACE = [ \t\r\n]+

KEYWORDS = ("if" | "while" | "for" | "array" | "function")
OPERATORS = ("+" | "-" | "*" | "/" | "=" | "<>" | "<" | ">" | "<=" | ">=" )
LPAREN = ("(")
RPAREN = (")")
LBRACKET = ("[")
RBRACKET = ("]")

%%

<YYINITIAL> {
    "begin"    { return symbol(12,"begin"); }
    "end"      { return symbol(sym.END); }
    "write"    { return symbol(sym.WRITE); }
    "read"     { return symbol(sym.READ); }
    {KEYWORDS}   {
        System.out.println("создан такой токен (Jflex): " + symbol(sym.KEYWORD, yytext()));
        return symbol(sym.KEYWORD, yytext());
    }
    {NUMBER}     {System.out.println("создан такой токен (Jflex): "+symbol(sym.NUMBER, Integer.parseInt(yytext())));
                    return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {STRING}     {System.out.println("создан такой токен (Jflex): "+symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)));
          return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       {System.out.println("создан такой токен (Jflex): "+symbol(sym.CHAR, yytext().charAt(1)));
          return symbol(sym.CHAR, yytext().charAt(1)); }
    {OPERATORS}  { System.out.println("создан такой токен (Jflex): "+symbol(sym.OPERATOR, yytext()));
          return symbol(sym.OPERATOR, yytext()); }
    {LPAREN}     {System.out.println("создан такой токен (Jflex): "+symbol(sym.LPAREN, yytext()));
          return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     {System.out.println("создан такой токен (Jflex): "+symbol(sym.RPAREN, yytext()));
          return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   { System.out.println("создан такой токен (Jflex): "+symbol(sym.LBRACKET, yytext()));
          return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   { System.out.println("создан такой токен (Jflex): "+symbol(sym.RBRACKET, yytext()));
          return symbol(sym.RBRACKET, yytext()); }
    ":="         {System.out.println("создан такой токен (Jflex): "+symbol(sym.ASSIGN, yytext()));
          return symbol(sym.ASSIGN, yytext()); }
    ";"          { System.out.println("создан такой токен (Jflex): "+symbol(sym.SEMICOLON, yytext()));
          return symbol(sym.SEMICOLON, yytext()); }
    {IDENTIFIER} {System.out.println("создан такой токен (Jflex): "+symbol(sym.IDENTIFIER, yytext()));
          return symbol(sym.IDENTIFIER, yytext()); }
    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { System.out.println("Проигнорирован пробел"); /* Пропускаем пробелы */ }
    [ \t]+       { System.out.println("Проигнорирована табулиция");  /* Игнорировать пробелы и табуляцию */ }
    [\n]+        { System.out.println("Проигнорирован перенос строки");/* Игнорировать переносы строк */ }
    <<EOF>> { System.out.println("создан такой токен(Jflex): " + symbol(sym.EOF, null));
          return symbol(sym.EOF, null); }
}

<COMMENT> {
    [^\n]* { System.out.println("Проигнорирован комментарий");/* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}