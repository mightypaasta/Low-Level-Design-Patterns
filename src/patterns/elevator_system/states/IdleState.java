package patterns.elevator_system.states;

import patterns.elevator_system.entities.Elevator;
import patterns.elevator_system.entities.Request;
import patterns.elevator_system.enums.Direction;

public class IdleState implements ElevatorState{

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        Direction direction = request.getDirection();
        if(direction==Direction.UP){
            elevator.setCurrentState(new MovingUpState());
        }else if(direction==Direction.DOWN){
            elevator.setCurrentState(new MovingDownState());
        }
        elevator.addRequest(request);
        elevator.moveElevator();
    }

    @Override
    public void moveElevator(Elevator elevator) {

    }
}
