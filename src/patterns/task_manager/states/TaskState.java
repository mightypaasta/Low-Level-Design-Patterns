package patterns.task_manager.states;

import patterns.task_manager.enums.TaskStatus;
import patterns.task_manager.models.Comment;
import patterns.task_manager.models.ITask;
import patterns.task_manager.models.Task;
import patterns.task_manager.models.User;

public abstract class TaskState implements ITask {
    public Task task;

    public TaskState(Task task){
        this.task = task;
    }
}

