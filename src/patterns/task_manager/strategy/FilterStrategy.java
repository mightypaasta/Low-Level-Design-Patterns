package patterns.task_manager.strategy;

public interface FilterStrategy<T,I>{
    T execute(T tasks, I filter);
}

