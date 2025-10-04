package patterns.coffee_machine;

public class Ingredient{
    private final IngredientsName name;
    private int quantity;

    public Ingredient(IngredientsName name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public IngredientsName getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void updateQuantity(int quantity) {
        int newValue = this.quantity + quantity;
        if(newValue>=0){
            this.quantity = newValue;
        }else{
            throw new IllegalArgumentException("Cannot have -ve quantity");
        }
    }
}