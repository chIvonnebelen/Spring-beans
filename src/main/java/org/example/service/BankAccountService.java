package org.example.service;

import org.example.entity.BankAccount;
import org.example.entity.User;
import org.example.exception.ExceptValidationBankAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class BankAccountService {
    Scanner sc = new Scanner(System.in);
    BankAccount bankAccount = new BankAccount();
    DebitCardService debitCardService = new DebitCardService();
    UserService userService = new UserService();
    User user=new User();


    public User loginBankAccount(List<User> customers){
        boolean validator;
        String username="";

        do {
            try {
                validator = false;
                System.out.println("Enter the username :");
                username = sc.next();

                boolean validarUserName = false;
                for (User aux : customers) {
                    if (aux.getUsername().equals(username)) {
                        System.out.println("username correct");
                        loginPassword(aux.getPassword());
                        System.out.println("Welcome "+ aux.getName());
                        user=aux;
                        validarUserName= true;
                        break;
                    }
                }

                if (!validarUserName) {
                    throw new ExceptValidationBankAccount("The usermane don not exist, try again.If you do not have an account, go to register");
                }

            } catch (Exception e) {
                validator=true;
                e.printStackTrace();
                System.out.println("User doesn´t exist");
            }
        } while (validator);

        return user;
    }

    public void loginPassword(String userPassword) {
        boolean validator;
        String password = "";

        do {
            try {
                validator = false;
                System.out.println("Enter the password :");
                password = sc.next();

                if (userPassword.equals(password)) {
                    System.out.println("-------");
                } else {
                    throw new ExceptValidationBankAccount("Password incorrect invalid, try again");
                }
            } catch (Exception e) {
                validator = true;
                e.printStackTrace();
            }
        } while (validator);
    }

//    public BankAccount createBankAccount(ArrayList<User> customers){
//        actualBalance(bankAccount);
//        deposit(bankAccount);
//        transfer(bankAccount);
//        extraction(bankAccount);
//        bankAccount.setDebit(debitCardService.createDebitCard(customers));
//        return bankAccount;
//        //  bankAccount.setMenu(unionService.menu());// chequear
//    }

    public void cbuValidation(BankAccount bankAccount) {
        System.out.println("Enter you alias : ");
        boolean aliasControl=true;
        do {
            aliasControl=false;
            try {
                String cbu = sc.nextLine();
                if (cbu.equals("")) {
                    throw new ExceptValidationBankAccount("Invalid CBU");
                } else {
                    bankAccount.setCbu(bankAccount.getCbu());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } while (!aliasControl);
    }

    public void initialDeposit(BankAccount bankAccount){

        System.out.println("Enter the first initial deposit");
        Double firstDeposit = sc.nextDouble();
        boolean depositControl;
        do{
            depositControl=false;
            try{
                if (firstDeposit >0.0){
                    bankAccount.setBalance(bankAccount.getBalance()+firstDeposit);
                    System.out.println("Congratulation of the first deposit" );
                    System.out.println("Your actual balance is " + bankAccount.getBalance());

                }else{
                    throw new ExceptValidationBankAccount("Invalid Initial Deposit");
                }
            }catch (Exception e){
                e.printStackTrace();
                depositControl = true;
            }
        }while (depositControl);

    }
    public void actualBalance(BankAccount bankAccount) {

        if (bankAccount.getBalance() < 0.0) {
            throw new ExceptValidationBankAccount("Insufficient balance");
        }else{
            System.out.println("Your account balance is " + bankAccount.getBalance());
        }
    }

    public void  deposit(BankAccount bankAccount) {
        System.out.println(" Enter the deposit amount");
        double newDeposit = sc.nextDouble();
        if (newDeposit > 0.0) {
            bankAccount.setBalance(bankAccount.getBalance() + newDeposit);
            System.out.println("You deposit : " + newDeposit);
            System.out.println("Your actual balance is " + bankAccount.getBalance());
        } else{
            throw new ExceptValidationBankAccount("Invalid deposit");
        }
    }
    public void transfer(BankAccount bankAccount, List<User> customers ){
        System.out.println("Enter the account number where you want to deposit");
        int cuentaTransf = sc.nextInt();

        for (User usuario: customers) {
            usuario.getAccounts();
            for(BankAccount cuenta:usuario.getAccounts()) {
                cuenta.getCbu();
                if(cuenta.getCbu() == cuentaTransf) {
                    System.out.println("Enter the transfer amount");
                    double amount= sc.nextDouble();

                    if(amount > bankAccount.getBalance()){
                        throw new ExceptValidationBankAccount("Insuficient amount");
                    }else{
                        bankAccount.setBalance(bankAccount.getBalance()-amount);
                        System.out.println("Successful transfer");
                        System.out.println("Your actual balance : "+ bankAccount.getBalance());
                        cuenta.setBalance(cuenta.getBalance() + amount);
                    }
                }
            }
        }

    }
    public void extraction(BankAccount bankAccount){
        System.out.println("Enter the extraction amount");
        double amount= sc.nextDouble();
        if(amount<0.0 ){
            throw new ExceptValidationBankAccount("Invalid amount");
        } else if(amount > bankAccount.getBalance()){
            throw new ExceptValidationBankAccount("Insuficient amount");
        }else {
            bankAccount.setBalance(bankAccount.getBalance()-amount);
            System.out.println("Successful extraction");
            System.out.println("Your actual balance : "+ bankAccount.getBalance());
        }

    }
}
