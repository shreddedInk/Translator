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
    public class sym {
        public static final int EOF = 0;
        public static final int KEYWORD = 1;
        public static final int IDENTIFIER = 2;
        public static final int NUMBER = 3;
        public static final int STRING = 4;
        public static final int CHAR = 5;

        public static final int PLUS = 6;
        public static final int MINUS = 7;
        public static final int OR = 8;
        public static final int STAR = 9;
        public static final int DIV = 10;
        public static final int AND = 11;
        public static final int NOT = 12;// + - * /
        public static final int ASSIGN = 13;      // :=
        public static final int EQ = 14;          // =
        public static final int NEQ = 15;         // <>
        public static final int LT = 16;         // <
        public static final int GT = 17;         // >
        public static final int LEQ = 18;        // <=
        public static final int GEQ = 19;        // >=

        public static final int BOOLEAN_LITERAL = 20;

        public static final int LPAREN = 21;
        public static final int RPAREN = 22;
        public static final int LBRACKET = 23;
        public static final int RBRACKET = 24;
        public static final int BEGIN = 25;
        public static final int END = 26;
        public static final int WRITE = 27;
    }

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
BOOLEAN = ("true"|"false")
ASSIGN = ":="
ALLOP = ("+"|"-"|"or")
NOT = "not"
MULOP = ("*"|"/"|"and")
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

    {ALLOP}         {
          switch(yytext()) {
              case "+":  return symbol(sym.PLUS);
              case "-": return symbol(sym.MINUS);
              case "or":  return symbol(sym.OR);
              default: throw new RuntimeException("Недопустимый оператор: " + yytext());
          }
      }
    {MULOP}         {
          switch(yytext()) {
                case "*":  return symbol(sym.STAR);
                case "/": return symbol(sym.DIV);
                case "and":  return symbol(sym.AND);
                default: throw new RuntimeException("Недопустимый оператор: " + yytext());
            }
      }
    {NOT}           { return symbol(sym.NOT, yytext());}
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