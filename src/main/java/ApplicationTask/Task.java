package ApplicationTask;

public class Task {
    private String task_text;


    public  Task(String task_text){this.task_text=task_text;}

    @Override
    public String toString() {
        return String.format(
                "%s",
                task_text);
    }

    public String getTask_text() {
        return task_text;
    }

    public void setTask_text(String task_text) {
        this.task_text = task_text;
    }


}
