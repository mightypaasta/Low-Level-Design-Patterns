package patterns.traffic_control_system.src;

enum SignalColor {
    GREEN,
    YELLOW,
    RED
}

enum Direction {
    EAST,
    WEST,
    NORTH,
    SOUTH
}

interface SignalState{
    void handle(TrafficLight light);
}

class GreenState implements SignalState{

    @Override
    public void handle(TrafficLight context) {
        context.setSignalColor(SignalColor.GREEN);
        context.setNextState(new YellowState());
    }
}

class YellowState implements SignalState{

    @Override
    public void handle(TrafficLight context) {
        context.setSignalColor(SignalColor.YELLOW);
        context.setNextState(new RedState());
    }
}

class RedState implements SignalState{

    @Override
    public void handle(TrafficLight context) {
        context.setSignalColor(SignalColor.RED);
        // Red signal is a stable state and will only change to green
        // by the intersection controller.
        context.setNextState(new RedState());
    }
}