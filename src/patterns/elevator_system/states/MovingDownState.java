package patterns.elevator_system.states;

import patterns.elevator_system.entities.Elevator;
import patterns.elevator_system.entities.Request;
import patterns.elevator_system.enums.Direction;

import java.util.TreeSet;

public class MovingDownState implements ElevatorState{

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int targetFloor = request.getTargetFloor();
        elevator.addDownRequest(targetFloor);
    }

    @Override
    public void moveElevator(Elevator elevator) {
        TreeSet<Integer> downRequests = elevator.getUpRequests();
        while(!downRequests.isEmpty()){
            int nextFloor = downRequests.last();
            downRequests.remove(nextFloor);
            elevator.setCurrentFloor(nextFloor);
            elevator.run();
        }
    }
}
