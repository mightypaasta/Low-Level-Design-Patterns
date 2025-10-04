package patterns.traffic_control_system.src;

interface TrafficObserver {
    void update(int intersectionId, Direction direction, SignalColor color);
}

class CentralTrafficMonitor implements TrafficObserver{

    @Override
    public void update(int intersectionId, Direction direction, SignalColor color) {
        System.out.println("At intersection "+intersectionId + " " + direction + " has "+ color + " light");
    }
}
