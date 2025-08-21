package patterns.vending_machine;

import java.util.HashMap;
import java.util.Map;

record Item(String name, String code, double price) {
}

enum Coin {
    PENNY,
    NICKEL,
    QUARTER,
    DIME
}

class Inventory{
    private final Map<String,Integer> stockMap;
    private final Map<String,Item> itemMap;

    public Inventory(){
        this.stockMap = new HashMap<>();
        this.itemMap = new HashMap<>();
    }

    public void addItem(String code, Item item, int quantity){
        if(this.stockMap.containsKey(code)){
            stockMap.put(code, this.stockMap.get(code)+quantity);
        }else{
            stockMap.put(code, quantity);
        }

        if(!this.itemMap.containsKey(code)){
            itemMap.put(code, item);
        }
    }

    public void reduceStock(String code){
        int prev_quantity = this.stockMap.get(code);
        int new_quantity = prev_quantity - 1;
        new_quantity = new_quantity < 0 ? prev_quantity : new_quantity;
        this.stockMap.put(code,new_quantity);
    }

    public Item getItem(String code){
        if(this.itemMap.containsKey(code)){
            return this.itemMap.get(code);
        } else {
            throw new IllegalArgumentException("No item found with the provided code.");
        }

    }

    public boolean isAvailable(String code){
        return this.stockMap.get(code) > 0 ;
    }
}