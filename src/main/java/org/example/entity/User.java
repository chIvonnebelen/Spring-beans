package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String lastName;
    private String email;
    private Integer dni;
    private Integer age;
    private String username;
    private String password;
    // private BankAccount account;
    private List<BankAccount> accounts = new ArrayList<BankAccount>();

    public User() {
    }

    public User(String name, String lastName, String email, Integer dni, Integer age, String username, String password) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User(String name, String lastName, String email, Integer dni, Integer age, String username, String password, List<BankAccount> accounts) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.age = age;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
