package io.sever86.domain;
import java.sql.Timestamp;

public class Persona {

    public Persona(Integer human_no, String first_name,String last_name,
                   String second_name,Timestamp date_birth,float tax,String login,String password) {
        this.humaNo = human_no;
        this.firstName = first_name;
        this.lastName =last_name;
        this.secondName =second_name;
        this.dateBirth =date_birth;
        this.tax=tax;
        this.login=login;
        this.password=password;
    }

    public Persona() {
    }

    @Override
    public String toString() {
        return String.format(
               "%s %s %s %s %s %s %s %s", humaNo, firstName, lastName, secondName, dateBirth,tax,login,password );
    }





    private Integer humaNo;
    private String firstName;
    private String lastName;
    private String secondName;
    private Timestamp dateBirth;
    private float tax;
    private String login;
    private String password;


    public Integer getHuman_no() {
        return humaNo;
    }

    public void setHuman_no(Integer human_no) {
        this.humaNo = human_no;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Timestamp getDate_birth() {
        return dateBirth;
    }

    public void setDate_birth(Timestamp date_birth) {
        this.dateBirth = date_birth;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

