import java_cup.runtime.*;
import java.nio.charset.StandardCharsets;

%%

%class PascalLexer
%unicode
%cup
%line
%column

%state COMMENT

%{
    public class sym {
        public static final int KEYWORD = 1;
        public static final int IDENTIFIER = 2;
        public static final int NUMBER = 3;
        public static final int STRING = 4;
        public static final int CHAR = 5;
        public static final int OPERATOR = 6;
        public static final int LPAREN = 7;
        public static final int RPAREN = 8;
        public static final int LBRACKET = 9;
        public static final int RBRACKET = 10;
        public static final int BEGIN = 11;
        public static final int END = 12;
        public static final int WRITE = 13;
        public static final int EOF = 0;
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
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
SEMICOLON = (";")
COLON = (":")

BEGIN = ("begin")
END = ("end")
WRITE = ("write")

INTEGER = ("integer")
BOOLEAN = ("boolean")
REAL = ("REAL")
VAR = ("var")


%%

<YYINITIAL> {
    {KEYWORDS}   { return symbol(sym.KEYWORD, yytext()); }
    {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
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
      {IDENTIFIER} { return symbol(sym.IDENTIFIER, yytext()); }
    "//"         { yybegin(COMMENT); }
    {WHITESPACE} { /* Пропускаем пробелы */ }
}

<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}