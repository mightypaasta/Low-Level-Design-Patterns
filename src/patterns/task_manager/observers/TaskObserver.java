package patterns.task_manager.observers;

public class TaskObserver{
    public void update(String message){
        System.out.println("Task Update: "+message);
    }
}
