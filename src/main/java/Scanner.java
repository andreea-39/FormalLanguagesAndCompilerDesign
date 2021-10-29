package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Scanner {

    SymbolTable symbolTable;
    ArrayList<String> tokens;
    ArrayList<Pair<String, Integer>> pif;

    public Scanner(SymbolTable symbolTable, String tokenFile) {
        this.symbolTable = symbolTable;
        this.pif = new ArrayList<>();
        readTokens(tokenFile);


    }

    private void readTokens(String file){
        tokens = new ArrayList<>();
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line;
            while((line= buffer.readLine())!=null){
                tokens.add(line.strip());
            }
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }


    public boolean isOperator(String token){

        String[] operators = {"+", "-", "*", "/","%",
                "<", "<=", "=", ">=", ">",
                ">>", "<<",
                "==", "!=",
                "&&", "||",
                "!", "?",
                "&", "^", "~", "|",
                "++", "--",
                ","};

//        System.out.println(Arrays.toString(operators));

        return Arrays.asList(operators).contains(token);
    }

//    public boolean isPartOfOperator(String token){
//        String[] operators = {
//                "<=",">=",
//                ">>", "<<",
//                "==", "!=",
//                "&&", "||",
//                "++", "--",};
//        return Arrays.asList(operators).contains(token);
//    }

    public boolean isSeparator(String token){
        String[] separators = {" ","," ,";", ":",
                "[", "]", "{", "}", "(", ")", "\n", ""};

        return Arrays.asList(separators).contains(token);
    }

//    public boolean isSeparator(String token){
//        String[] separators = {"integer"};
//
//        return Arrays.asList(separators).contains(token);
//    }


    public boolean isReservedWord(String token){
        String[] reservedWords = {"integer"," float", "char", "string", "boolean",
                "true", "false",
                "if", "then", "else", "for", "range", "while", "do",
                "read", "write",
                "begin", "end"};

        return Arrays.asList(reservedWords).contains(token);
    }

    public boolean isIdentifier(String token){
        return token.matches("^[_a-zA-Z][a-zA-Z0-9]{0,255}");
//        return token.matches("^[_a-z]\\w*$");
    }

    public boolean isConstant(String token){
        return token.matches("[0]|[+-]?[0-9]*|\"([a-zA-Z0-9])*\"|true|false|'[a-zA-Z0-9]']$");
    }
    
    private void writePIF() throws IOException{
        FileWriter outputFile = new FileWriter("PIF.out");

        for (Pair<String, Integer> stringIntegerPair : pif) {
            outputFile.write(stringIntegerPair + "\n");
        }
        outputFile.flush();
        outputFile.close();
    }

    private void writeST() throws IOException{
        FileWriter outputFile = new FileWriter("ST.out");

        outputFile.write(String.valueOf(symbolTable));

        outputFile.flush();
        outputFile.close();
    }


    public void scan(String filePath) throws IOException{
        boolean correct = true;

        try(BufferedReader buffer = new BufferedReader(new FileReader(filePath))){

            String line;
            int lineIndex = 0;
            while((line= buffer.readLine()) != null && correct){
                ArrayList<String> receivedTokens = tokenGenerator(line.strip());
                for (int index=0; index<receivedTokens.size(); index++) {
                    if (receivedTokens.get(index).equals(" ") || receivedTokens.get(index).isEmpty()) {
                        continue;
                    }
                    if (isOperator(receivedTokens.get(index)) || isSeparator(receivedTokens.get(index)) || isReservedWord(receivedTokens.get(index))) {
                        pif.add(new Pair(receivedTokens.get(index), -1));
//                        System.out.println(receivedTokens.get(index));
                    } else if (isIdentifier(receivedTokens.get(index))) {
                        symbolTable.add(receivedTokens.get(index));
                        pif.add(new Pair("IDENTIFIER", symbolTable.getPosition(receivedTokens.get(index))));
//                        System.out.println(receivedTokens.get(index));
                    } else if (isConstant(receivedTokens.get(index))) {
                        symbolTable.add(receivedTokens.get(index));
                        pif.add(new Pair("CONSTANT", symbolTable.getPosition(receivedTokens.get(index))));
//                        System.out.println(receivedTokens.get(index));
                    } else {
                        System.err.println("Lexical error! Undefined token " + receivedTokens.get(index) + " on line " + lineIndex);
                        correct = false;
                    }
                }
                lineIndex += 1;

                }

        }catch(IOException exception){
            exception.printStackTrace();
        }
        if(correct){
            System.out.println("ok");
            writePIF();
            writeST();
        }
    }

    private ArrayList<String> tokenGenerator(String line){
        ArrayList<String> tokens = new ArrayList<>();
        int index1=0;
        int index2=0;

        while(index2<line.length()){
            if (line.charAt(index2) == '"') {
                tokens.add(line.substring(index1, index2).strip());
                index1=index2;
                index2+=1;
                if(line.substring(index1+1).indexOf('"')==-1){
                    index2=line.length()-1;
            }else{
                    while(line.charAt(index2) != '"' && index2 < line.length()){
                        index2+=index1;

                    }
                    index2+=1;
                }
            }

//            System.out.println(String.valueOf(line.charAt(index2)));
            if (isOperator(String.valueOf(line.charAt(index2))) || isSeparator(String.valueOf(line.charAt(index2))) || String.valueOf(line.charAt(index2)).equals("")){
//                System.out.println(line.substring(index1, index2).strip());
//                System.out.println(line.substring(index2, index2).strip());
                tokens.add(line.substring(index1, index2).strip());
                tokens.add(line.substring(index2, index2).strip());
                index1 = index2 + 1;
            }
            index2 += 1;

        }
        return tokens;
    }

}
