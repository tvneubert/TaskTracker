package ch.zhaw.prog2.tasktracker.oservables;

public interface ObservableTaskView {
    public void addListener(ITaskEvent listener);
    public void removeListener(ITaskEvent listener);
}
