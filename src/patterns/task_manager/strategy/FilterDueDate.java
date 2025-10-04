package patterns.task_manager.strategy;

import patterns.task_manager.models.Task;
import patterns.task_manager.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilterDueDate implements FilterStrategy<List<Task>, Date> {

    @Override
    public List<Task> execute(List<Task> tasks, Date filter) {
        List<Task> filterList = new ArrayList<Task>();
        for(Task task: tasks){
            if(task.getDueDate().equals(filter)){
                filterList.add(task);
            }
            for(Task subTask: task.getSubTasks()){
                if(subTask.getDueDate().equals(filter)){
                    filterList.add(task);
                }
            }
        }
        return filterList;
    }
}

