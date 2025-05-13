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
        public static final int EOF = 0;
        public static final int KEYWORD = 1;
        public static final int IDENTIFIER = 2;
        public static final int NUMBER = 3;
        public static final int STRING = 4;
        public static final int CHAR = 5;

        public static final int ARITH_OP = 6;   // + - * /
        public static final int ASSIGN = 7;      // :=
        public static final int EQ = 8;          // =
        public static final int NEQ = 9;         // <>
        public static final int LT = 10;         // <
        public static final int GT = 11;         // >
        public static final int LEQ = 12;        // <=
        public static final int GEQ = 13;        // >=

        public static final int BOOLEAN_LITERAL = 14;

        public static final int LPAREN = 15;
        public static final int RPAREN = 16;
        public static final int LBRACKET = 17;
        public static final int RBRACKET = 18;
        public static final int BEGIN = 19;
        public static final int END = 20;
        public static final int WRITE = 21;
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

KEYWORDS = ("if"|"while"|"for"|"array"|"function"|"and"|"or"|"not")
BOOLEAN = ("true"|"false")
ARITHMETIC_OP = ("+"|"-"|"*"|"/")
ASSIGN = ":="
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
    {BOOLEAN}       { return symbol(sym.BOOLEAN_LITERAL, yytext().equals("true")); }

    {KEYWORDS}      { return symbol(sym.KEYWORD, yytext()); }

    {IDENTIFIER}    { return symbol(sym.IDENTIFIER, yytext()); }
    {NUMBER}        { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
    {STRING}        {
        String str = new String(yytext().getBytes(), StandardCharsets.UTF_8);
        str = str.substring(1, str.length() - 1);
        return symbol(sym.STRING, str);
    }
    {CHAR}          { return symbol(sym.CHAR, yytext().charAt(1)); }

    {ARITHMETIC_OP} { return symbol(sym.ARITH_OP, yytext()); }

    {ASSIGN}        { return symbol(sym.ASSIGN, yytext()); }
    {COMPARISON}    {
        switch(yytext()) {
            case "=":  return symbol(sym.EQ);
            case "<>": return symbol(sym.NEQ);
            case "<":  return symbol(sym.LT);
            case ">":  return symbol(sym.GT);
            case "<=": return symbol(sym.LEQ);
            case ">=": return symbol(sym.GEQ);
            default: throw new RuntimeException("Недопустимый оператор: " + yytext());
        }
    }

    {LPAREN}        { return symbol(sym.LPAREN); }
    {RPAREN}        { return symbol(sym.RPAREN); }
    {LBRACKET}      { return symbol(sym.LBRACKET); }
    {RBRACKET}      { return symbol(sym.RBRACKET); }
    {BEGIN}         { return symbol(sym.BEGIN); }
    {END}           { return symbol(sym.END); }
    {WRITE}         { return symbol(sym.WRITE); }

    "//"            { yybegin(COMMENT); }
    {WHITESPACE}    { /* Игнорируем пробелы */ }
}

<COMMENT> {
    [^\n]* { /* Игнорируем содержимое комментария */ }
    \n     { yybegin(YYINITIAL); }
}