package patterns.elevator_system.entities;

import patterns.elevator_system.enums.Direction;
import patterns.elevator_system.enums.RequestSource;

public class Request {
    private final RequestSource source;
    private final int targetFloor;
    private final Direction direction;

    public Request(RequestSource source, Direction direction, int targetFloor){
        this.source = source;
        this.direction = direction;
        this.targetFloor = targetFloor;
    }

    public RequestSource getSource(){ return source; }
    public Direction getDirection(){ return  direction; }
    public int getTargetFloor(){ return targetFloor; }
}
