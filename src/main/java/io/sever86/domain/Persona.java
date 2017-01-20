package io.sever86.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Persona {

    public Persona(Integer id, String first_name, String last_name,
                   String second_name, Timestamp date_birth, BigDecimal tax, String login, String password) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.secondName = second_name;
        this.dateBirth = date_birth;
        this.tax = tax;
        this.login = login;
        this.password = password;
    }

    public Persona() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s %s %s %s %s", id, firstName, lastName, secondName, dateBirth, tax, login, password);
    }


    private Integer id;
    private String firstName;
    private String lastName;
    private String secondName;
    private Timestamp dateBirth;
    private BigDecimal tax;
    private String login;
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getDatebirth() {
        return dateBirth;
    }

    public void setDatebirth(Timestamp date_birth) {
        this.dateBirth = date_birth;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
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

