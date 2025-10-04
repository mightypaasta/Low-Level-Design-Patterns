package patterns.task_manager;

import patterns.task_manager.enums.TaskPriority;
import patterns.task_manager.enums.TaskStatus;
import patterns.task_manager.models.Comment;
import patterns.task_manager.models.Task;
import patterns.task_manager.models.TaskManager;
import patterns.task_manager.models.User;
import patterns.task_manager.observers.TaskObserver;

import java.time.LocalDate;
import java.util.Date;

public class TaskManagerDemo {
  public void run() {
    TaskManager instance = TaskManager.getInstance();
    User Bob = new User("Bob", "bobthebuilder@gmail.com");
    User Alice = new User("Alice", "aliceinwonderland@gmail.com");
    User Robert = new User("Robert", "robertdowneyjr@gmail.com");

    TaskObserver observer = new TaskObserver();

    Task buildTask = new Task("Build the house", "Build the house for the Robert.", TaskPriority.MEDIUM, new Date(),
        Bob);
    Task blueprintTask = new Task("Prepare the blueprint", "Prepare the blueprint for Robert's house.",
        TaskPriority.HIGH, new Date(), Alice);
    Task paymentTask = new Task("Payment for the house", "Robert have to pay for the house.", TaskPriority.LOW,
        new Date(), Robert);
    Task demoTask = new Task("Demo task", "Give demo to the Robert", TaskPriority.LOW, new Date(), Robert);

    // Create two new tasks for Robert
    // Create two new task for Alice
    Task prepareDemo = new Task("Prepare demo", "Prepare the demo for the Robert", TaskPriority.MEDIUM, new Date(),
        Alice);

    buildTask.addObserver(observer);
    blueprintTask.addObserver(observer);
    paymentTask.addObserver(observer);

    instance.addTask(buildTask);
    instance.addTask(blueprintTask);
    instance.addTask(paymentTask);

    buildTask.getState().updateStatus(TaskStatus.PENDING);
    blueprintTask.getState().updateStatus(TaskStatus.IN_PROGRESS);
    blueprintTask.getState().updateStatus(TaskStatus.COMPLETED);

    Task fetchMaterails = new Task("Get materials", "Get materials for the house", TaskPriority.HIGH, new Date(), Bob);
    buildTask.getState().addSubTask(fetchMaterails);
    fetchMaterails.getState().updateStatus(TaskStatus.IN_PROGRESS);
    Comment whereIsMyHouse = new Comment("Where is my house Bob. When it will be ready", Robert);
    buildTask.getState().addComment(whereIsMyHouse);
    fetchMaterails.getState().updateStatus(TaskStatus.COMPLETED);
    Comment bobComment = new Comment("It will be ready shortly Mr Robert.", Bob);
    buildTask.getState().addComment(bobComment);

  }
}
