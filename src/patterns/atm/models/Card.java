package patterns.atm.models;

import java.util.UUID;

public class Card {
    private final String number;
    private final String pin;

    public Card(String cardNumber, String pin){
        number = cardNumber;
        this.pin = pin;
    }

    public boolean checkPin(String pin){
        return this.pin.equals(pin);
    }

    public String getCardNumber(){
        return this.number;
    }
}
