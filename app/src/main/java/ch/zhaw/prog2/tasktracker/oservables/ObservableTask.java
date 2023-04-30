package ch.zhaw.prog2.tasktracker.oservables;

public interface ObservableTask {
    public void addListener(TaskEvent listener);
    public void removeListener(TaskEvent listener);
}
