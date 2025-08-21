package patterns.vending_machine;

interface IVendingMachine{
    void selectItem(String code);
    void dispense();
    void insertCoin(Coin coin);
    void refund();
}

class VendingMachine implements IVendingMachine{
    private VendingMachine instance;
    private final Inventory inventory;
    private double balance;
    private String selectedItemCode;
    private VendingMachineState state;

    private VendingMachine(Inventory inventory, int balance){
        this.inventory = inventory;
        this.balance = balance;
        this.state = new IdleState(this.getInstance());
    }

    public VendingMachine getInstance(){
        if(this.instance == null){
            Inventory inventory = new Inventory();
            return new VendingMachine(inventory,0);
        }
        return this.instance;
    }

    public void addItem(String code, String name, double price, int quantity){
        Item item = new Item(name,code,price);
        this.instance.inventory.addItem(item.code(),item,quantity);
    }

    public Item getSelectedItem(){
        return this.inventory.getItem(this.selectedItemCode);
    }

    public void setSelectedItemCode(String code){
        this.selectedItemCode = code;
    }

    @Override
    public void selectItem(String code){
        if(this.inventory.isAvailable(code)){
            this.setSelectedItemCode(code);
        }
    }

    @Override
    public void dispense() {

    }

    @Override
    public void insertCoin(Coin coin) {
        switch(coin){
            case PENNY -> this.balance += 0.25;
            case NICKEL -> this.balance += 0.50;
            case QUARTER -> this.balance += 0.75;
            case DIME -> this.balance += 1.00;
        }
    }

    @Override
    public void refund() {

    }


}

abstract class VendingMachineState implements IVendingMachine{

    protected VendingMachine machine;

    protected VendingMachineState(VendingMachine machine){
        this.machine = machine;
    }

}

class SelectedItemState extends VendingMachineState{
    protected SelectedItemState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        this.machine.selectItem(code);
    }

    @Override
    public void dispense() {
        throw new IllegalCallerException("Cannot dispense without money.");
//        this.machine.dispense();
    }

    @Override
    public void insertCoin(Coin coin) {
        this.machine.insertCoin(coin);
    }

    @Override
    public void refund() {
        throw new IllegalCallerException("Cannot refund in selected item state.");
    }
}

class HasMoneyState extends VendingMachineState{

    protected HasMoneyState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        throw new IllegalCallerException("Cannot select item in HasMoneyState");
    }

    @Override
    public void dispense() {
        this.machine.dispense();
    }

    @Override
    public void insertCoin(Coin coin) {
        this.machine.insertCoin(coin);
    }

    @Override
    public void refund() {
        throw new IllegalCallerException("Cannot refund in HasMoneyState");
    }
}

class DispensingState extends VendingMachineState{

    protected DispensingState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        throw new IllegalCallerException("Cannot select item in DispensingState");
    }

    @Override
    public void dispense() {
        this.machine.dispense();
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalCallerException("Cannot insert coin in DispensingState");
    }

    @Override
    public void refund() {
        this.machine.refund();
    }
}

class IdleState extends VendingMachineState{

    protected IdleState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        this.machine.selectItem(code);
    }

    @Override
    public void dispense() {
        throw new IllegalCallerException("Cannot dispense in IdleState");
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalCallerException("Cannot insert coin in IdleState");

    }

    @Override
    public void refund() {
        throw new IllegalCallerException("Cannot refund in IdleState");
    }
}
