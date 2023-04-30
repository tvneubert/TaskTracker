package ch.zhaw.prog2.tasktracker.oservables;

public interface ObservableProject {
    public void addListener(ProjectEvent listener);
    public void removeListener(ProjectEvent listener);
}
