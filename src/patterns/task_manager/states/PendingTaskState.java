package patterns.task_manager.states;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.enums.TaskStatus;
import patterns.task_manager.models.Comment;
import patterns.task_manager.models.Task;
import patterns.task_manager.models.User;

public class PendingTaskState extends TaskState{


    public PendingTaskState(Task task) {
        super(task);
    }

    @Override
    public void assignUser(User user) {
        task.assignUser(user);
    }

    @Override
    public void updateStatus(TaskStatus status) {
        if(status==TaskStatus.PENDING) return;
        task.updateStatus(status);
        if(status == TaskStatus.IN_PROGRESS){
            task.setState(new PendingTaskState(task));
        }else if(status == TaskStatus.COMPLETED){
            task.setState(new InProgressTaskState(task));
        }
    }

    @Override
    public void addSubTask(Task subTask) {
        task.addSubTask(subTask);
    }

    @Override
    public void addComment(Comment comment) {
        task.addComment(comment);
    }

    @Override
    public void changePriority(TaskPriority priority) {
        task.changePriority(priority);
    }
}
