package patterns.coffee_machine;

import java.util.ArrayList;
import java.util.List;

public abstract class Coffee{
    private final String name;
    private final int price;
    private final List<Ingredient> ingredients;
    
    public Coffee(String name, int price, List<Ingredient> ingredients){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}

class Espresso extends Coffee{

    public Espresso() {
        super("Espresso", 4, generateIngredientList());
    }

    private static List<Ingredient> generateIngredientList(){
        List<Ingredient> ingredientList= new ArrayList<Ingredient>();
        ingredientList.add(new Ingredient(IngredientsName.COFFEE_BEANS.ARABICA,5));
        ingredientList.add(new Ingredient(IngredientsName.WATER.NORMAL, 10));
        return ingredientList;
    }
}

class Cappuccino extends Coffee{

    public Cappuccino() {
        super("Cappuccino", 6, generateIngredientList());
    }

    private static List<Ingredient> generateIngredientList(){
        List<Ingredient> ingredientList= new ArrayList<Ingredient>();
        ingredientList.add(new Ingredient(IngredientsName.COFFEE_BEANS.ARABICA,3));
        ingredientList.add(new Ingredient(IngredientsName.WATER.NORMAL, 5));
        ingredientList.add(new Ingredient(IngredientsName.MILK.COW, 10));
        ingredientList.add(new Ingredient(IngredientsName.CREAM.HEAVY,5));
        return ingredientList;
    }
}

class Latte extends Coffee{

    public Latte() {
        super("Latte", 5, generateIngredientList());
    }

    private static List<Ingredient> generateIngredientList(){
        List<Ingredient> ingredientList= new ArrayList<Ingredient>();
        ingredientList.add(new Ingredient(IngredientsName.COFFEE_BEANS.ARABICA,5));
        ingredientList.add(new Ingredient(IngredientsName.WATER.NORMAL, 10));
        return ingredientList;
    }
}