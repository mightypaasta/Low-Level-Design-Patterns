package patterns.elevator_system.observers;

import patterns.elevator_system.entities.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator);
}

