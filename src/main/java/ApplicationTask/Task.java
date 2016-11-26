package ApplicationTask;

public class Task {

    //public Task(int id, String text){this.id=id; this.text=text;}
    public Task(){}

    @Override
    public String toString() {
        return String.format(
                "%s","%s", id,
                text);
    }

    private int id;
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}

