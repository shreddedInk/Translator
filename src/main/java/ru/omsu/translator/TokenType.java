package ru.omsu.translator;

public enum TokenType {
    KEYWORD,
    IDENTIFIER,
    OPERATOR,
    INTEGER_LITERAL,
    CHAR_LITERAL,
    STRING_LITERAL,
    LPAREN, RPAREN, LBRACKET, RBRACKET,
    BEGIN, END, WRITE
}