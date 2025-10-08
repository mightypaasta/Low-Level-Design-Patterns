package patterns.elevator_system.states;

import patterns.elevator_system.entities.Elevator;
import patterns.elevator_system.entities.Request;
import patterns.elevator_system.enums.Direction;

import java.util.TreeSet;

public class MovingUpState implements ElevatorState{

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int targetFloor = request.getTargetFloor();
        elevator.addUpRequest(targetFloor);
    }

    @Override
    public void moveElevator(Elevator elevator) {
        TreeSet<Integer> upRequests = elevator.getUpRequests();
        while(!upRequests.isEmpty()){
            int nextFloor = upRequests.first();
            upRequests.remove(nextFloor);
            elevator.setCurrentFloor(nextFloor);
            elevator.run();
        }
    }
}
