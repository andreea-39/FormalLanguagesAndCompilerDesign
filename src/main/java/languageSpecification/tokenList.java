package main.java.languageSpecification;

import java.util.Arrays;

public class tokenList {


    String[] separators = {" ", "," ,";", ":",
            "[", "]", "{", "}", "(", ")"};

    String[] operators = {"+", "-", "*", "/",
            "<", "<=", "=", ">=", ">",
            ">>", "<<",
            "==", "!=",
            "&&", "||",
            "!",
            "&", "^", "~", "|",
            "++", "--",
            ","};

    String[] reservedWords = {"integer", "float", "char", "string", "boolean",
            "true", "false",
            "if", "then", "else", "for", "range", "while", "do",
            "read", "write"};

    public tokenList(String[] separators, String[] operators, String[] reservedWords) {
        this.separators = separators;
        this.operators = operators;
        this.reservedWords = reservedWords;
    }

    public String[] getSeparators() {
        return separators;
    }

    public String[] getOperators() {
        return operators;
    }

    public String[] getReservedWords() {
        return reservedWords;
    }
}
