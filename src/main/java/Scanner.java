package main.java;


import main.java.languageSpecification.tokenList;

public class Scanner {
    main.java.languageSpecification.tokenList tokenList;

    public Scanner(tokenList languageSpecification) {
        this.tokenList = languageSpecification;
    }



    public boolean isOperator(String token){
        for(String operator: tokenList.getOperators()){
            if(operator.equals(token)){
                return true;
            }
        }
        return false;
    }

    public boolean isSeparator(String token){
        for(String separator: tokenList.getSeparators()){
            if(separator.equals(token)){
                return true;
            }
        }
        return false;
    }
    public boolean isReservedWord(String token){
        for(String reservedWord: tokenList.getReservedWords()){
            if(reservedWord.equals(token)){
                return true;
            }
        }
        return false;
    }

    public boolean isIdentifier(String token){
        return token.matches("~[a-zA-Z][a-zA-Z0-9]{0,255}");
    }

    public boolean isConstant(String token){
        return token.matches("[0]|[+-]?[0-9]*|\"([a-zA-Z0-9])*\"|true|false|'[a-zA-Z0-9]']$");
    }

}
