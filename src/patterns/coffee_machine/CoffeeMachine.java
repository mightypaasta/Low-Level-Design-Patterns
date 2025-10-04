package patterns.coffee_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface ICoffeeMachine{
    void display();
    void select(int input);
    void dispense();
    void updateInventory(IngredientsName name, int quantity);
    boolean makePayment(Payment payment);
}

public class CoffeeMachine implements ICoffeeMachine {
    private static CoffeeMachine instance;
    private final Inventory inventory;
    private CoffeeMachineState state;
    private final List<Coffee> menu;
    private Payment payment;
    private Coffee coffee;
    private boolean paymentSuccess;

    private CoffeeMachine(Inventory inventory, List<Coffee> menu){
        this.inventory = inventory;
        this.menu = menu;
        setState(new IdleState(this));
    }

    public static CoffeeMachine getInstance(){
        if(instance == null){
            Map<IngredientsName,Ingredient> ingredients = new HashMap<IngredientsName,Ingredient>();
            ingredients.put(IngredientsName.MILK.COW,new Ingredient(IngredientsName.MILK.COW,10));
            ingredients.put(IngredientsName.COFFEE_BEANS.ARABICA, new Ingredient(IngredientsName.COFFEE_BEANS.ARABICA, 100));
            ingredients.put(IngredientsName.COFFEE_BEANS.ROBUSTA, new Ingredient(IngredientsName.COFFEE_BEANS.ROBUSTA, 100));
            ingredients.put(IngredientsName.WATER.NORMAL, new Ingredient(IngredientsName.WATER.NORMAL, 100));
            ingredients.put(IngredientsName.CREAM.HEAVY, new Ingredient(IngredientsName.CREAM.HEAVY, 10));
            Inventory newInventory = new Inventory(ingredients);

            List<Coffee> newMenu = new ArrayList<Coffee>();
            newMenu.add(new Espresso());
            newMenu.add(new Cappuccino());
            newMenu.add(new Latte());

            instance = new CoffeeMachine(newInventory,newMenu);
        }
        return instance;
    }

    public CoffeeMachineState getState(){
        return state;
    }

    public Coffee getCoffee(){
        return coffee;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public void setState(CoffeeMachineState state){
        this.state = state;
    }

    public void display(){
        int i = 1;
        menu.forEach(coffee -> System.out.println(i+". "+ coffee.getName()));
    }

    public void select(int input){
        switch(input) {
            case 1:
                coffee = new Espresso();
                break;
            case 2:
                coffee = new Cappuccino();
                break;
            case 3:
                coffee = new Latte();
                break;
            default:
                break;
        }
    }

    public void dispense(){
        try {
            if (hasEnoughIngredients()) {
                System.out.println("Dispensing " + coffee.getName());
            } else {
                throw new Exception("Unable to dispense coffee. Insufficient ingredients.");
            }
        } catch(Exception e){
            System.out.println("Exception thrown: "+e.getMessage());
        }
    }

    public boolean makePayment(Payment payment) {
        this.payment = payment;
        paymentSuccess = processPayment();
        return paymentSuccess;
    }

    public void updateInventory(IngredientsName name, int quantity){
        try {
            inventory.updateQuantity(name, quantity);
        }catch(IllegalArgumentException e){
            System.out.println("Exception thrown: "+e.getMessage());
        }
    }

    private boolean hasEnoughIngredients(){
        for(Ingredient ingredient: coffee.getIngredients()){
            if(ingredient.getQuantity() > inventory.getQuantity(ingredient.getName())){
                return false;
            }
        }
        return true;
    }

    private boolean processPayment(){
        int changeAmt = payment.getAmount_paid() - coffee.getPrice();
        if(changeAmt>0){
            returnChange(changeAmt);
            return true;
        }else return changeAmt == 0;
    }

    private void returnChange(int amount){
        System.out.println("Returning change amount: "+amount);
    }

}