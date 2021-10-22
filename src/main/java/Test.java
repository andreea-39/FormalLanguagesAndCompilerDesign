package main.java;

public class Test {
    public static void main(String[] args){
        SymbolTable symbolTable = new SymbolTable(5);

        String[] symbolList = {"s1","s2","s3","s4", "s5"};

        System.out.println("Test add");
        for (String symbol : symbolList) {
            assert(symbolTable.add(symbol));
        }
        for (String symbol : symbolList) {
            assert(!symbolTable.add(symbol));
        }
        System.out.println("Finished test add");

        System.out.println("Test search");
        for (String symbol : symbolList) {
            assert (symbolTable.search(symbol));
        }
        System.out.println("Finished test search");


        System.out.println("Test getPosition");
        assert(symbolTable.getPosition("s1").getFirst().toString()=="4");
        assert(symbolTable.getPosition("s1").getSecond().toString()=="0");

        assert(symbolTable.getPosition("s2").getFirst().toString()=="0");
        assert(symbolTable.getPosition("s2").getSecond().toString()=="0");

        assert(symbolTable.getPosition("s3").getFirst().toString()=="1");
        assert(symbolTable.getPosition("s3").getSecond().toString()=="0");

        assert(symbolTable.getPosition("s4").getFirst().toString()=="3");
        assert(symbolTable.getPosition("s4").getSecond().toString()=="0");

        assert(symbolTable.getPosition("s5").getFirst().toString()=="4");
        assert(symbolTable.getPosition("s5").getSecond().toString()=="0");

        System.out.println("Finished test getPosition");

        System.out.println("Test remove");
        for (String symbol : symbolList) {
            assert(symbolTable.remove(symbol));
        }
        for (String symbol : symbolList) {
            assert(!symbolTable.remove(symbol));
        }
        System.out.println("Finished test remove");
    }
}
