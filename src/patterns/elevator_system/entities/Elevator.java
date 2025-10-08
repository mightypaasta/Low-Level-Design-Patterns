package patterns.elevator_system.entities;

import patterns.elevator_system.enums.Direction;
import patterns.elevator_system.observers.ElevatorObserver;
import patterns.elevator_system.states.ElevatorState;
import patterns.elevator_system.states.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    private final String id;
    private final List<ElevatorObserver> observers;
    private boolean isRunning;
    private ElevatorState currentState;
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;
    private final AtomicInteger currentFloor;

    public Elevator(){
        this.id = UUID.randomUUID().toString();
        this.observers = new ArrayList<>();
        this.isRunning = false;
        this.currentState = new IdleState();
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
        this.currentFloor = new AtomicInteger(0);
    }

    public void addObserver(ElevatorObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        observers.forEach(observer -> observer.update(this));
    }

    public void setCurrentState(ElevatorState state){
        this.currentState = state;
    }

    public Direction getDirection(){
        return currentState.getDirection();
    }

    public void setCurrentFloor(int floor){
        currentFloor.set(floor);
        stopElevator();
        notifyAllObservers();
    }

    public void moveElevator(){
        currentState.moveElevator(this);
    }

    public void stopElevator(){
        this.isRunning = false;
    }

    public void run(){
        this.isRunning = true;
    }

    public void addRequest(Request request){
        currentState.addRequest(this,request);
    }

    public void addUpRequest(int floor){
        upRequests.add(floor);
    }

    public void addDownRequest(int floor){
        downRequests.add(floor);
    }

    public TreeSet<Integer> getUpRequests(){ return this.upRequests; }

    public TreeSet<Integer> getDownRequests(){ return this.downRequests; }

    public boolean isRunning(){ return isRunning; }

    public int getCurrentFloor(){ return currentFloor.get(); }
}
