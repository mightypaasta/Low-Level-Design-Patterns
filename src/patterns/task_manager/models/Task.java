package patterns.task_manager.models;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.enums.TaskStatus;
import patterns.task_manager.observers.TaskObserver;
import patterns.task_manager.states.PendingTaskState;
import patterns.task_manager.states.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task implements ITask {
    private final String id;
    private final String title;
    private final String description;
    private TaskPriority priority;
    private TaskStatus status;
    private final Date dueDate;
    private User assignee;
    private boolean isSubTask;
    private final List<Task> subTasks = new ArrayList<Task>();
    private TaskObserver observer;
    private final List<Comment> comments = new ArrayList<Comment>();
    private final List<String> history = new ArrayList<String>();
    private TaskState state;

    public Task(String title, String description, TaskPriority priority, Date dueDate, User assignee){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.dueDate = dueDate;
        this.assignee = assignee;
        state = new PendingTaskState(this);
    }

    @Override
    public void assignUser(User user) {
        this.assignee = user;
        String message = String.format("TaskID: %s has been assigned to %s",id,assignee.getUsername());
        notifyAllObservers(message);
    }

    @Override
    public void updateStatus(TaskStatus status) {
        this.status = status;
        String message = String.format("TaskID: %s has been updated to %s",id,status);
        notifyAllObservers(message);
    }

    @Override
    public void addSubTask(Task subTask) {
        subTask.changeToSubTask();
        subTasks.add(subTask);
        String message = String.format("New subtask id: %s has been added to task id: %s",subTask.id,id);
        notifyAllObservers(message);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
        String message = String.format("Comment id: %s is added to the task id: %s",comment.getId(),id);
        notifyAllObservers(message);
    }

    @Override
    public void changePriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public TaskPriority getPriority(){
        return priority;
    }

    public List<Task> getSubTasks(){
        return subTasks;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public List<String> getHistory(){
        return history;
    }

    public TaskState getState(){
        return state;
    }

    public User getAssignee(){
        return assignee;
    }

    public boolean isSubTask(){
        return isSubTask;
    }

    public void changeToSubTask(){
        this.isSubTask = true;
    }

    public void addObserver(TaskObserver observer){
        System.out.println("Observer added to task id: "+id);
        this.observer = observer;
    }

    public void removeObserver(){
        this.observer = null;
    }

    public void setState(TaskState state){
        this.state = state;
    }

    private void addToHistory(String message){
        history.add(message);
    }

    private void notifyAllObservers(String message){
        if(observer!=null){
            observer.update(message);
        }else{
            System.out.println("No observers found for task id: "+id);
        }
    }

}
