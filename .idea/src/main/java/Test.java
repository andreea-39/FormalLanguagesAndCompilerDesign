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
//        assert(symbolTable.getPosition("s1").getFirst().toString()=="4");
//        System.out.println(symbolTable.getPosition("s1").getFirst());
        for (String symbol : symbolList) {
            System.out.println(symbolTable.getPosition(symbol));
        }
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
