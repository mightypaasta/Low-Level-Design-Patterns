package patterns.atm.chainofresponsibility;

public abstract class NoteDispenser implements DispenserChain {
    int noteValue;
    int numNotes;
    DispenserChain nextChain;

    public NoteDispenser(int noteValue, int numNotes){
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    public void setNextChain(DispenserChain chain){
        this.nextChain = chain;
    }

    public synchronized void dispense(int amount){
        if(amount>=noteValue){
            int dispenseNoteCount = Math.min(amount/noteValue,numNotes);
            int remainingAmount = amount - (dispenseNoteCount * noteValue);

            if(dispenseNoteCount>0){
                System.out.println("Dispensing "+dispenseNoteCount+" notes of "+noteValue);
                numNotes -= dispenseNoteCount;
            }

            if(remainingAmount > 0 && nextChain!=null){
                nextChain.dispense(remainingAmount);
            }

        }else if(nextChain!=null){
            nextChain.dispense(amount);
        }
    }

    public synchronized boolean canDispense(int amount){
        int numToDispense = Math.min(amount/noteValue, numNotes);
        int remainingAmount = amount - (numToDispense*noteValue);

        if(remainingAmount == 0)    return true;
        if(remainingAmount>0 && nextChain!=null){
            nextChain.canDispense(remainingAmount);
        }
        return false;
    }
}
