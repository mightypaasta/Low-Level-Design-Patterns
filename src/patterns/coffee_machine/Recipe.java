package patterns.coffee_machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Recipe {
    private final Map<IngredientsName,Integer> ingredients = new HashMap<>();
    private Inventory inventory;
    private int price;

    public Recipe(Map<IngredientsName,Integer> ingredients, int price) {
        this.ingredients.putAll(ingredients);
        this.price = price;
    }

    void create(Inventory inventory){
        ingredients.forEach((name,quantity)->{
            try {
                inventory.updateQuantity(name, -quantity);
            }catch (IllegalArgumentException e){
                System.out.println("Thrown exception: "+e.getMessage());
            }
        });
    }

    int getPrice(){
        return price;
    }
}

