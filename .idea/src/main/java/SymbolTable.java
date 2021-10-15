import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<ArrayList<String>> symbolList;
    private int size;

    public SymbolTable(int size){
        this.size = size;
        this.symbolList = new ArrayList<>();

        for(int index=0; index<size; index++){
            this.symbolList.add(new ArrayList<>());
        }

    }

    private int hash(String symbol) {
        return symbol.codePoints().sum() % size;
    }

    public boolean search(String symbol){
        return symbolList.get(hash(symbol)).contains(symbol);
    }

    public boolean add(String symbol){
        int hashValue = hash(symbol);

        if(symbolList.get(hashValue).contains(symbol)){
            return false;
        }

        symbolList.get(hashValue).add(symbol);
        return true;
    }

    public boolean remove(String symbol){
        int hashValue = hash(symbol);

        if(!symbolList.get(hashValue).contains(symbol)){
            return false;
        }
        symbolList.get(hashValue).remove(symbol);
        return true;
    }

    public Pair getPosition(String symbol){
        if (search(symbol)==false)
            return null;

        return new Pair(hash(symbol), symbolList.get(hash(symbol)).toString());
//        return new Pair(hash(symbol), symbolList.get(hash(symbol)).indexOf(symbol));
    }

    public int getSize(){
        return size;
    }
}
