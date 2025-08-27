package patterns.traffic_control_system.src;

import java.util.concurrent.TimeUnit;

public class TrafficControlDemo {

    public static void demo(){
        TrafficControlSystem system = TrafficControlSystem.getInstance();

        system.addIntersectionController(1,2000,2000);
        system.addIntersectionController(2,3000,1000);

        try{
            TimeUnit.SECONDS.sleep(8000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        system.stopSystem();
    }

}
