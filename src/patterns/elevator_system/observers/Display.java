package patterns.elevator_system.observers;

import patterns.elevator_system.entities.Elevator;

public class Display() implements ElevatorObserver{

    @Override
    public void update(Elevator elevator) {
        System.out.println("Elevator is currently at floor no: "+elevator.getCurrentFloor());
    }
}
