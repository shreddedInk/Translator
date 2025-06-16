package ru.omsu.translator.data;
import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;
import ru.omsu.translator.Token;
import ru.omsu.translator.CustomSymbol;
import ru.omsu.translator.cup.sym;
import java_cup.runtime.Symbol;
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
        System.out.println("Создан CustomSymbol (JFlex): " + customSymbol);
        return new Symbol(type, customSymbol);
    }

    private Symbol symbol(int type, Object value) {
        Token token = new Token(type, value);
        if (type < 0) {
            throw new RuntimeException("Вывело -1 для этого: " + yytext());
        }
        CustomSymbol customSymbol = new CustomSymbol(type, token);
        System.out.println("Создан CustomSymbol (JFlex): " + customSymbol);
        return new Symbol(type, customSymbol);
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

    "if"       { return symbol(sym.IF, yytext()); }
    "while"    { return symbol(sym.WHILE, yytext()); }
    "for"      { return symbol(sym.FOR, yytext()); }
    "array"    { return symbol(sym.ARRAY, yytext()); }
    "function" { return symbol(sym.FUNCTION, yytext()); }

    "+"   { return symbol(sym.PLUS, yytext()); }
    "-"   { return symbol(sym.MINUS, yytext()); }
    "*"   { return symbol(sym.MULTIPLY, yytext()); }
    "/"   { return symbol(sym.DIVIDE, yytext()); }
    "="   { return symbol(sym.EQ, yytext()); }
    "<>"  { return symbol(sym.NE, yytext()); }
    "<"   { return symbol(sym.LT, yytext()); }
    ">"   { return symbol(sym.GT, yytext()); }
    "<="  { return symbol(sym.LE, yytext()); }
    ">="  { return symbol(sym.GE, yytext()); }

    {STRING}     { return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       { return symbol(sym.CHAR, yytext().charAt(1)); }
    {LPAREN}     { return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     { return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   { return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   { return symbol(sym.RBRACKET, yytext()); }
    ":="         { return symbol(sym.ASSIGN, yytext()); }
    ";"          { return symbol(sym.SEMICOLON, yytext()); }
    {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
    "//"         { yybegin(COMMENT); }
    {NUMBER}     { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }

    [ \t]+       { System.out.println("Проигнорирована табуляция"); }
    [\n]+        { System.out.println("Проигнорирован перенос строки"); }
    {WHITESPACE} { System.out.println("Проигнорирован пробел"); }
    <<EOF>>      { return symbol(sym.EOF, null); }
}

<COMMENT> {
    [^\n]* { System.out.println("Проигнорирован комментарий"); }
    \n     { yybegin(YYINITIAL); }
}
