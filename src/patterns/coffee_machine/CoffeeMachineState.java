package patterns.coffee_machine;

public abstract class CoffeeMachineState implements ICoffeeMachine {
    CoffeeMachine machine;

    CoffeeMachineState(CoffeeMachine machine) {
        this.machine = machine;
    }

}

class IdleState extends CoffeeMachineState {

    IdleState(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void display() {
        machine.display();
    }

    @Override
    public void select(int input) {
        machine.select(input);
        machine.setState(new SelectedState(machine));
    }

    @Override
    public void dispense() {
        System.out.println("Cannot dispense before selection");
    }

    @Override
    public void updateInventory(IngredientsName name, int quantity) {
        machine.updateInventory(name, quantity);
    }

    @Override
    public boolean makePayment(Payment payment) {
        System.out.println("Please select from the menu first.");
        return false;
    }
}

class SelectedState extends CoffeeMachineState {

    SelectedState(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void display() {
        System.out.println("Selected coffee is: " + machine.getCoffee().getName());
    }

    @Override
    public void select(int input) {
        System.out.println("Already selected: " + machine.getCoffee().getName());
    }

    @Override
    public void dispense() {
        System.out.println("Cannot dispense coffeee before payment.");
    }

    @Override
    public void updateInventory(IngredientsName name, int quantity) {
        System.out.println("Cannot update inventory at selection stage");
    }

    @Override
    public boolean makePayment(Payment payment) {
        boolean success = machine.makePayment(payment);
        if(success){
            machine.setState(new PaymentState(machine));
        }else{
            machine.setState(new SelectedState(machine));
        }
        return success;
    }
}

class PaymentState extends CoffeeMachineState {

    PaymentState(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void display() {
        System.out.println("Processing payment");
    }

    @Override
    public void select(int input) {
        System.out.println("Cannot take input during payment state.");
    }

    @Override
    public void dispense() {
        if (machine.isPaymentSuccess()) {
            machine.dispense();
            machine.setState(new DispensingState(machine));
        }
    }

    @Override
    public void updateInventory(IngredientsName name, int quantity) {
        System.out.println("Cannot update inventory in payment state.");
    }

    @Override
    public boolean makePayment(Payment payment) {
        System.out.println("Already paid.");
        return false;
    }
}

class DispensingState extends CoffeeMachineState {

    DispensingState(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void display() {
        System.out.println("Dispensing coffee.");
    }

    @Override
    public void select(int input) {
        System.out.println("No selection available during dispensing state.");
    }

    @Override
    public void dispense() {
        System.out.println("Already dispensed coffee.");
    }

    @Override
    public void updateInventory(IngredientsName name, int quantity) {
        for (Ingredient ingredient : machine.getCoffee().getIngredients()) {
            updateInventory(ingredient.getName(), -ingredient.getQuantity());
        }
        System.out.println("Inventory adjusted.");
    }

    @Override
    public boolean makePayment(Payment payment) {
        System.out.println("Already paid.");
        return false;
    }
}