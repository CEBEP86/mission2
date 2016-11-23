package ApplicationTask;
import java.util.ArrayList;

public class Customer2 {

   // public Customer2(String text){this.list.add(text);}
     public Customer2(){}

    @Override
    public String toString() {
        return String.format(
                "%s",
                notes);
    }

    private  ArrayList<String> notes;

    public String getText() {
        String listString = "";
        for (String s : notes)
        {
            listString += s + "\t";
        }

        return listString;
    }

    public void setText(String text) {
        this.notes.add(text);
    }

}

