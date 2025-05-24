package ru.omsu.translator.data;

import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;
import ru.omsu.translator.java.Token;
import ru.omsu.translator.java.CustomSymbol;
import ru.omsu.translator.java.TypesTable;
import ru.omsu.translator.java.type_control.TypeExpression;

%%

%class PascalLexer
%unicode
%cup
%line
%column

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


VAR = ("var")
BEGIN = ("begin")
END = ("end" | "end.")

KEYWORDS = ("if" | "while" | "for" | "array" | "function")

OPERATORS = ("+" | "-" | "*" | "/" | "=" | "<>" | "<" | ">" | "<=" | ">=" | ":=")

LPAREN = ("(")
RPAREN = (")")
LBRACKET = ("[")
RBRACKET = ("]")
SEMICOLON = (";")
COLON = (":")


WRITE = ("write")

INTEGER = ("integer")
BOOLEAN = ("boolean")
REAL = ("real")


%%

<YYINITIAL> {

    {VAR} { return symbol(sym.VAR, yytext());}
    {KEYWORDS}   { return symbol(sym.KEYWORD, yytext()); }
    {NUMBER}     { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {STRING}     { return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       { return symbol(sym.CHAR, yytext().charAt(1)); }
    {OPERATORS}  { return symbol(sym.OPERATOR, yytext()); }
    {LPAREN}     { return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     {return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   {return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   {return symbol(sym.RBRACKET, yytext()); }
    {BEGIN}      {return symbol(sym.BEGIN, yytext()); }
    {END}        {return symbol(sym.END, yytext()); }
    {WRITE}        {return symbol(sym.WRITE, yytext()); }
    ";"          { return symbol(sym.SEMICOLON, yytext()); }
    "//"         { yybegin(COMMENT); }
    ":"          {return symbol(sym.COLON, yytext());}
    {WHITESPACE} { /* Пропускаем пробелы */ }
    {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
}

<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}