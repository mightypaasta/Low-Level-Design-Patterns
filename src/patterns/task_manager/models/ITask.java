package patterns.task_manager.models;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.enums.TaskStatus;

public interface ITask{
    void assignUser(User user);
    void updateStatus(TaskStatus status);
    void addSubTask(Task subTask);
    void addComment(Comment comment);
    void changePriority(TaskPriority priority);
}
