package patterns.traffic_control_system.src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficControlSystem {
    private static final TrafficControlSystem instance = new TrafficControlSystem();
    private final List<IntersectionController> controllerList = new ArrayList<IntersectionController>();
    private ExecutorService executorService;

    private TrafficControlSystem() {}

    public static TrafficControlSystem getInstance(){
        return instance;
    }

    public void addIntersectionController(int id, long greenDuration, long yellowDuration){
        IntersectionController controller = new IntersectionController.Builder(id)
                .withDuration(greenDuration,yellowDuration)
                .addObserver(new CentralTrafficMonitor())
                .build();
        this.controllerList.add(controller);
    }

    public void startSystem(){
        if(controllerList.isEmpty()){
            System.out.println("Cannot start the system. No intersection controllers found.");
        }
        System.out.println("Starting system");
        executorService = Executors.newFixedThreadPool(this.controllerList.size());
        controllerList.forEach(executorService::submit);
    }

    public void stopSystem(){
        controllerList.forEach(IntersectionController::stop);
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            executorService.shutdownNow();
        }
        System.out.println("All intersections are stopped. System is shut down");
    }

}
