package main.java;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SymbolTable symbolTable = new SymbolTable(8);
        Scanner scanner = new Scanner(symbolTable, "Token.in");

        scanner.scan("p3.txt");
    }
}
