package org.example.entity;

import org.springframework.stereotype.Component;

@Component
public class BankAccount {
    private double balance;
    private int cbu;
    private User user;
    private DebitCard debit;

    private static int cbuNumber = 1000;

    public BankAccount() {

    }

    public BankAccount(User user) {
        this.user = user;
        this.balance = 0.0;
        cbu = cbuNumber;
        cbuNumber++;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCbu() {
        return cbu;
    }

    public void setCbu(int cbu) {
        this.cbu = cbu;
    }

    public DebitCard getDebit() {
        return debit;
    }

    public void setDebit(DebitCard debit) {
        this.debit = debit;
    }



    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", cbu='" + cbu + '\'' +
                ", debit=" + debit +
                '}';
    }

}
