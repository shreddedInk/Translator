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
        CustomSymbol customSymbol = new CustomSymbol(type, token);
        System.out.println("Создан CustomSymbol (JFlex): " + customSymbol);
        return customSymbol;
    }

    private Symbol symbol(int type, Object value) {
        Token token = new Token(type, value);
        if (type < 0) {
            throw new RuntimeException("Вывело -1 для этого: " + yytext());
        }
        CustomSymbol customSymbol = new CustomSymbol(type, token);
        System.out.println("Создан CustomSymbol (JFlex): " + customSymbol);
        return customSymbol;
    }

    public void initialize() {
        yyreset(new java.io.StringReader(""));
    }
%}

IDENTIFIER         = [a-zA-Z_][a-zA-Z_0-9]*
NUMBER             = [0-9]+
STRING             = \'([^\\']|\\.)*\'
CHAR               = \'([^\\]|\\.)\'
WHITESPACE         = [ \t\r\n]+

KEYWORDS           = ("array"|"function")
IF                 = "if"
THEN               = "then"
ELSE               = "else"
WHILE              = "while"
FOR                = "for"
DO                 = "do"
TO                 = "to"
REPEAT             = "repeat"
UNTIL              = "until"
BOOLEAN_LITERAL    = ("true"|"false")
ASSIGN             = ":="
PLUS               = "+"
MINUS              = "-"
OR                 = "or"
STAR               = "*"
DIV                = "/"
AND                = "and"
NOT                = "not"
COMPARISON         = ("="|"<>"|"<"|">"|"<="|">=")

LPAREN             = "("
RPAREN             = ")"
LBRACKET           = "["
RBRACKET           = "]"
BEGIN              = "begin"
END                = "end"
WRITE              = "write"
READ               = "read"

INTEGER            = "integer"
BOOLEAN            = "boolean"
REAL               = "real"
VAR                = "var"
SEMICOLON          = ";"
COLON              = ":"
DOT                = "."

%%

<YYINITIAL> {

    {INTEGER}         { return symbol(sym.INTEGER, yytext()); }
    {BOOLEAN}         { return symbol(sym.BOOLEAN, yytext()); }
    {REAL}            { return symbol(sym.REAL, yytext()); }
    {VAR}             { return symbol(sym.VAR, yytext()); }
    {SEMICOLON}       { return symbol(sym.SEMICOLON, yytext()); }
    {COLON}           { return symbol(sym.COLON, yytext()); }
    {DOT}             { return symbol(sym.DOT, yytext()); }

    {BOOLEAN_LITERAL} { return symbol(sym.BOOLEAN_LITERAL, Boolean.parseBoolean(yytext())); }
    {KEYWORDS}        { return symbol(sym.KEYWORD, yytext()); }
    {IF}              { return symbol(sym.IF, yytext()); }
    {THEN}            { return symbol(sym.THEN, yytext()); }
    {ELSE}            { return symbol(sym.ELSE, yytext()); }
    {WHILE}           { return symbol(sym.WHILE, yytext()); }
    {FOR}             { return symbol(sym.FOR, yytext()); }
    {DO}              { return symbol(sym.DO, yytext()); }
    {TO}              { return symbol(sym.TO, yytext()); }
    {REPEAT}          { return symbol(sym.REPEAT, yytext()); }
    {UNTIL}           { return symbol(sym.UNTIL, yytext()); }

    {NUMBER}          { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {CHAR}            { return symbol(sym.CHAR, yytext().charAt(1)); }

    {PLUS}            { return symbol(sym.PLUS, yytext()); }
    {MINUS}           { return symbol(sym.MINUS, yytext()); }
    {OR}              { return symbol(sym.OR, yytext()); }
    {STAR}            { return symbol(sym.STAR, yytext()); }
    {DIV}             { return symbol(sym.DIV, yytext()); }
    {AND}             { return symbol(sym.AND, yytext()); }
    {NOT}             { return symbol(sym.NOT, yytext()); }

    {ASSIGN}          { return symbol(sym.ASSIGN, yytext()); }

    {COMPARISON} {
        switch(yytext()) {
            case "=":  return symbol(sym.EQ, yytext());
            case "<>": return symbol(sym.NEQ, yytext());
            case "<":  return symbol(sym.LT, yytext());
            case ">":  return symbol(sym.GT, yytext());
            case "<=": return symbol(sym.LEQ, yytext());
            case ">=": return symbol(sym.GEQ, yytext());
        }
    }

    {LPAREN}          { return symbol(sym.LPAREN, yytext()); }
    {RPAREN}          { return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}        { return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}        { return symbol(sym.RBRACKET, yytext()); }
    {BEGIN}           { return symbol(sym.BEGIN, yytext()); }
    {END}             { return symbol(sym.END, yytext()); }
    {WRITE}           { return symbol(sym.WRITE, yytext()); }
    {WRITE}           { return symbol(sym.READ, yytext()); }

    {IDENTIFIER}      { return symbol(sym.IDENTIFIER, symbol(sym.IDENTIFIER, yytext())); }

    {STRING} {
        String value = new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1);
        return symbol(sym.STRING, value);
    }

    "//"              { yybegin(COMMENT); }
    {WHITESPACE}      { System.out.println("Проигнорирован пробел"); }
    [ \t]+            { System.out.println("Проигнорирована табулиция"); }
    [\n]+             { System.out.println("Проигнорирован перенос строки"); }

    <<EOF>>           { return symbol(sym.EOF, null); }
}

<COMMENT> {
    [^\n]*            { /* Игнорируем содержимое комментария */ }
    \n                { yybegin(YYINITIAL); }
}
