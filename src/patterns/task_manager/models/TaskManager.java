package patterns.task_manager.models;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.strategy.FilterPriority;
import patterns.task_manager.strategy.FilterStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private static TaskManager instance;
    private final Map<String,Task> tasks = new HashMap<String,Task>();
    private FilterStrategy filterStrategy;

    private TaskManager(){
        this.filterStrategy = new FilterPriority();
    }

    public static TaskManager getInstance(){
        if(instance==null){
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task){
        tasks.put(task.getId(),task);
    }

    public void removeTask(Task task){
        tasks.remove(task.getId());
    }

    public Task getTaskById(String id){
        return tasks.get(id);
    }

    public FilterStrategy getStrategy(){
        return filterStrategy;
    }

    public void setFilterStrategy(FilterStrategy strategy){
        filterStrategy = strategy;
    }
}
