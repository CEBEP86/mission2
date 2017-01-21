package io.sever86.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Personal {

    private Integer id;
    private String firstName;
    private String lastName;
    private String secondName;
    private Timestamp dateBirth;
    private BigDecimal tax;
    private String login;
    private String password;
    private Boolean enabled;

    public Personal() {
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEabled() {
        return enabled;
    }

    public void setEabled(Boolean enabled) {
        this.enabled = enabled;
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

