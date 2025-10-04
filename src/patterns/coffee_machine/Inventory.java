package patterns.coffee_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<IngredientsName,Ingredient> ingredients = new HashMap<>();

    public Inventory(Map<IngredientsName,Ingredient> ingredients){
        this.ingredients.putAll(ingredients);
    }

    public int getQuantity(IngredientsName name){
        return ingredients.get(name).getQuantity();
    }

    public void updateQuantity(IngredientsName name, int quantity){
        int newValue = ingredients.get(name).getQuantity() + quantity;
        if(newValue>=0){
            ingredients.get(name).updateQuantity(newValue);
        }else{
            throw new IllegalArgumentException("Cannot remove quantity below 0");
        }
    }
}
