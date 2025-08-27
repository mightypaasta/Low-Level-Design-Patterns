package patterns.traffic_control_system.src;

import java.util.ArrayList;
import java.util.List;

class TrafficLight{
    private SignalColor signalColor = SignalColor.RED;
    private SignalState currentState = new RedState();
    private SignalState nextState = new GreenState();
    private final int intersectionId;
    private final List<TrafficObserver> observers = new ArrayList<TrafficObserver>();
    private final Direction direction;

    public TrafficLight(int intersectionId, Direction direction){
        this.intersectionId = intersectionId;
        this.direction = direction;
    }

    public void startGreen(){
        currentState = new GreenState();
        currentState.handle(this);
    }

    public void addObservers(TrafficObserver observer){
        observers.add(observer);
    }

    public void removeObservers(TrafficObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(TrafficObserver observer: observers){
            observer.update(intersectionId,direction,signalColor);
        }
    }

    public void transition(){
        currentState = nextState;
        currentState.handle(this);
    }

    public void setSignalColor(SignalColor color){
        if(signalColor != color){
            signalColor = color;
            notifyObservers();
        }
    }

    public void setNextState(SignalState state){
        nextState = state;
    }
}