package ApplicationTask;

public class Customer {

    public Customer(String text){this.text=text;}
    public Customer(){}

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

