package patterns.traffic_control_system.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IntersectionController implements Runnable {
    private final int id;
    private boolean running;
    private final Map<Direction,TrafficLight> trafficLights;
    private IntersectionState currentState;
    private final long greenDuration;
    private final long yellowDuration;

    public IntersectionController(int id, long greenDuration, long yellowDuration, Map<Direction, TrafficLight> lights){
        this.id = id;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.trafficLights = lights;
        this.currentState = new EastWestGreenState();
    }

    public void stop(){
        this.running = false;
    }

    @Override
    public void run(){
        while(running){
            try{
                currentState.update(this);
            }catch (InterruptedException e){
                System.out.println("Thread was interrupted");
            }
        }
    }

    public TrafficLight getLight(Direction direction){
        return trafficLights.get(direction);
    }

    public int getId(){
        return id;
    }

    public boolean isRunning(){
        return running;
    }

    public long getGreenDuration(){
        return greenDuration;
    }

    public long getYellowDuration(){
        return yellowDuration;
    }

    public void setCurrentState(IntersectionState currentState) {
        this.currentState = currentState;
    }

    static class Builder{
        private final int id;
        private long greenDuration;
        private long yellowDuration;
        private final List<TrafficObserver> observers = new ArrayList<TrafficObserver>();

        public Builder(int id){
            this.id = id;
        }

        public Builder withDuration(long greenDuration, long yellowDuration){
            this.greenDuration = greenDuration;
            this.yellowDuration= yellowDuration;
            return this;
        }

        public Builder addObserver(TrafficObserver observer){
            this.observers.add(observer);
            return this;
        }

        public IntersectionController build(){
            Map<Direction,TrafficLight> lights = new HashMap<>();
            for(Direction dir : Direction.values()){
                TrafficLight light= new TrafficLight(id,dir);
                observers.forEach(light::addObservers);
                lights.put(dir,light);
            }
            return new IntersectionController(id, greenDuration, yellowDuration, lights);
        }
    }
}
