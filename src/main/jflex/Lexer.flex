package org.example.data;
import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;
import org.example.java.Token;
import org.example.java.CustomSymbol;
import org.example.cup.*;
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
        return new CustomSymbol(type, new Token(type, yytext()));
    }

    private Symbol symbol(int type, Object value) {
        CustomSymbol customSymbol = new CustomSymbol(type, new Token(type, value));
        customSymbol.addAttribute("text", value);  // Добавляем атрибут с именем "text"
        return customSymbol;
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

KEYWORDS = ("if" | "while" | "for" | "array" | "function")

OPERATORS = ("+" | "-" | "*" | "/" | "=" | "<>" | "<" | ">" | "<=" | ">=" | ":=")

LPAREN = ("(")
RPAREN = (")")
LBRACKET = ("[")
RBRACKET = ("]")

BEGIN = ("begin")
END = ("end")
WRITE = ("write")

%%

<YYINITIAL> {
    {KEYWORDS}   { return symbol(sym.KEYWORD, yytext()); }
    {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
    {NUMBER}     { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {STRING}     { return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       { return symbol(sym.CHAR, yytext().charAt(1)); }
    {OPERATORS}  { return symbol(sym.OPERATOR, yytext()); }
    {LPAREN}     { return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     { return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   { return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   { return symbol(sym.RBRACKET, yytext()); }
    {BEGIN}      { return symbol(sym.BEGIN, yytext()); }
    {END}        { return symbol(sym.END, yytext()); }
    {WRITE}      { return symbol(sym.WRITE, yytext()); }
    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { /* Пропускаем пробелы */ }
}

<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}