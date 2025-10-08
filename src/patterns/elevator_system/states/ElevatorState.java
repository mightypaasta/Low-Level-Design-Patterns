package patterns.elevator_system.states;

import patterns.elevator_system.entities.Elevator;
import patterns.elevator_system.entities.Request;
import patterns.elevator_system.enums.Direction;

public interface ElevatorState {
    Direction getDirection();
    void addRequest(Elevator elevator, Request request);
    void moveElevator(Elevator elevator);
}

