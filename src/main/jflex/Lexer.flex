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
        System.out.println("Token created: " + type + " Value: " + yytext());
        if (type < 0) {
            throw new RuntimeException("Unexpected symbol type = -1 for text: " + yytext());
        }
        return symbol;
    }

    private Symbol symbol(int type, Object value) {
        Symbol symbol = new CustomSymbol(type, new Token(type, value));
        System.out.println("Token created: " + type + " Value: " + value);
        if (type < 0) {
            throw new RuntimeException("Unexpected symbol type = -1 for text: " + yytext());
        }
        return symbol;
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

KEYWORDS = ("if" | "while" | "for" | "array" | "function" | "read" | "write" | "begin" | "end")
OPERATORS = ("+" | "-" | "*" | "/" | "=" | "<>" | "<" | ">" | "<=" | ">=" )
LPAREN = ("(")
RPAREN = (")")
LBRACKET = ("[")
RBRACKET = ("]")

%%

<YYINITIAL> {
    {KEYWORDS}   {
           if (yytext().equals("read")) return symbol(sym.READ, yytext());
                      if (yytext().equals("write")) return symbol(sym.WRITE, yytext());
                      if (yytext().equals("begin")) return symbol(sym.BEGIN, yytext());
                      if (yytext().equals("end")) return symbol(sym.END, yytext());
                      return symbol(sym.KEYWORD, yytext());
      }
    {NUMBER}     { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {STRING}     { return symbol(sym.STRING, new String(yytext().getBytes(), StandardCharsets.UTF_8).substring(1, yytext().length() - 1)); }
    {CHAR}       { return symbol(sym.CHAR, yytext().charAt(1)); }
    {OPERATORS}  { return symbol(sym.OPERATOR, yytext()); }
    {LPAREN}     { return symbol(sym.LPAREN, yytext()); }
    {RPAREN}     { return symbol(sym.RPAREN, yytext()); }
    {LBRACKET}   { return symbol(sym.LBRACKET, yytext()); }
    {RBRACKET}   { return symbol(sym.RBRACKET, yytext()); }
    ":="         { return symbol(sym.ASSIGN, yytext()); }
    ";"          { return symbol(sym.SEMICOLON, yytext()); }
    {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { /* Пропускаем пробелы */ }
}

<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}