package patterns.atm.chainofresponsibility;

public interface DispenserChain {
    boolean canDispense(int amount);
    void dispense(int amount);
    void setNextChain(DispenserChain chain);
}

