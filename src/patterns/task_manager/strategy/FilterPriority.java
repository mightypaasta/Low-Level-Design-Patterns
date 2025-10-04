package patterns.task_manager.strategy;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.models.Task;

import java.util.ArrayList;
import java.util.List;

public class FilterPriority implements FilterStrategy<List<Task>,TaskPriority> {

    public List<Task> execute(List<Task> tasks, TaskPriority filter) {
        List<Task> filterList = new ArrayList<Task>();
        for(Task task: tasks){
            if(task.getPriority().equals(filter)){
                filterList.add(task);
            }
            for(Task subTask: task.getSubTasks()){
                if(subTask.getPriority().equals(filter)){
                    filterList.add(task);
                }
            }
        }
        return filterList;
    }
}

