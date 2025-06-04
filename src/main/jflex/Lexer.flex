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
        CustomSymbol customSymbol = new CustomSymbol(type, token);
        System.out.println("создан такой CustomSymbol (Jflex): " + customSymbol);
        return customSymbol;
    }

    private Symbol symbol(int type, Object value) {
        Token token = new Token(type, value);
        if (type < 0) {
            throw new RuntimeException("Вывело -1 для этого: " + yytext());
        }
        CustomSymbol customSymbol = new CustomSymbol(type, token);
        System.out.println("создан такой CustomSymbol (Jflex): " + customSymbol);
        return customSymbol;
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
    "begin"    { return symbol(sym.BEGIN); }
    "end"      { return symbol(sym.END); }
    "write"    { return symbol(sym.WRITE); }
    "read"     { return symbol(sym.READ); }
    {KEYWORDS}   {
        return symbol(sym.KEYWORD, yytext());
    }
    {STRING}     {return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       {return symbol(sym.CHAR, yytext().charAt(1)); }
    {OPERATORS}  {return symbol(sym.OPERATOR, yytext()); }
    {LPAREN}     {return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     {return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   {return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   {return symbol(sym.RBRACKET, yytext()); }
    ":="         {return symbol(sym.ASSIGN, yytext()); }
    ";"          {return symbol(sym.SEMICOLON, yytext()); }
    {IDENTIFIER} {return symbol(sym.IDENTIFIER, yytext()); }
    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { System.out.println("Проигнорирован пробел"); /* Пропускаем пробелы */ }
        {NUMBER}     {return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    [ \t]+       { System.out.println("Проигнорирована табулиция");  /* Игнорировать пробелы и табуляцию */ }
    [\n]+        { System.out.println("Проигнорирован перенос строки");/* Игнорировать переносы строк */ }
    <<EOF>> {return symbol(sym.EOF, null); }
}

<COMMENT> {
    [^\n]* { System.out.println("Проигнорирован комментарий");/* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}