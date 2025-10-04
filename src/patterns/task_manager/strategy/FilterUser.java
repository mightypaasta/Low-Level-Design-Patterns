package patterns.task_manager.strategy;

import patterns.task_manager.models.Task;
import patterns.task_manager.models.User;

import java.util.ArrayList;
import java.util.List;

public class FilterUser implements FilterStrategy<List<Task>, User>{

    @Override
    public List<Task> execute(List<Task> tasks, User filter) {
        List<Task> filterList = new ArrayList<Task>();
        for(Task task: tasks){
            if(task.getAssignee().equals(filter)){
                filterList.add(task);
            }
            for(Task subTask: task.getSubTasks()){
                if(subTask.getAssignee().equals(filter)){
                    filterList.add(task);
                }
            }
        }
        return filterList;
    }
}
