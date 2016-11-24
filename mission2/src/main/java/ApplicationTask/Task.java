package ApplicationTask;

public class Task {

    public Task(String text){this.text=text;}
    public Task(){}

    @Override
    public String toString() {
        return String.format(
                "%s",
                text);
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

