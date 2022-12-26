package org.example.service;

import org.example.entity.BankAccount;
import org.example.entity.User;
import org.example.exception.ExceptApp;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuService {

    private UserService userService;
    private BankAccountService bankAccountService;
    private DebitCardService debitCardService;
    private List<User> customers;
    private BankAccount bankAccount;
    private List<String> dataUsuarios;

    public MenuService (ApplicationContext context){
        userService= (UserService) context.getBean("userService");
        bankAccountService = (BankAccountService) context.getBean("bankAccountService");
        debitCardService=(DebitCardService) context.getBean("debitCardService");
        customers = inicialiarDatos();
        bankAccount= (BankAccount) context.getBean("bankAccount");
        dataUsuarios=userService.showUser(customers);

    }

   // BankAccount bankAccount;
    Scanner sc = new Scanner(System.in);
   // UserService userService = new UserService();
    //BankAccountService bankAccountService = new BankAccountService();
    //List<User> customers = inicialiarDatos();
    //DebitCardService debitCardService = new DebitCardService();
   // List<String> dataUsuarios = userService.showUser(customers);

    public void menuPrincipal() {

        User user= new User();
        boolean band;
        List<String> dataUsuarios = userService.showUser(customers);
        System.out.println(dataUsuarios);
        do {
            try {
                band = false;
                int i;
                System.out.println("CHOOSE ONE OPTION ");
                System.out.println("---------------------------------------------------------------");
                System.out.println("1. Create user");
                System.out.println("");
                System.out.println("2. Enter to your banking account");
                System.out.println("");
                System.out.println("3. Print all the data");
                System.out.println("");
                System.out.println("4. Leave the program");
                i = sc.nextInt();

                switch (i) {
                    case 1:
                        if (!userService.verifyUser(customers)) {
                            System.out.println("You can create a user account, continue");
                            System.out.println(" Complete the dates : ");
                            user = userService.createUser(customers);
                            customers.add(user);
                            user.getAccounts().add(new BankAccount(user));
                            System.out.println("Thank you for joining our bank ");
                            System.out.println("We have assignes you a new account");
                            System.out.println("Your account number is: " + user.getAccounts().get(0).getCbu());
                            System.out.println("Your account balance is: " + user.getAccounts().get(0).getBalance());
                            do {
                                menuBankAccount(user);
                            }

                            while (menuBankAccount(user) < 7);

                        } else {
                            userService.verifyUser(customers);
                            System.out.println("The limit  clients has been exceeded ");
                        }
                        break;

                    case 2:
                        System.out.println("Welcome to your banking account ");
                        user = bankAccountService.loginBankAccount(customers);
                        do {
                            menuBankAccount(user);
                        }
                        while (menuBankAccount(user) < 7);
                        break;
                    case 3:
                        System.out.println("Data");
                        System.out.println(dataUsuarios);
                        break;
                    case 4:
                        System.out.println("Thanks for using our services");
                        System.out.println(user.getName());
                        break;

                    default:
                        throw new ExceptApp("The number is not available. Try again:");
                }

            } catch (Exception e) {
                e.printStackTrace();
                band = true;
            }

        } while (band);

    }

    public int menuBankAccount(User user) { // debit card atraves de bankAccount, mismo de usuario

        BankAccount bankAccount = new BankAccount();
        int numValitor = 0;
        System.out.println("Choose one option ");
        System.out.println("--------------------------------");
        System.out.println("1. Know the status of your account : ");
        System.out.println("2. Deposit");
        System.out.println("3. Transaction");
        System.out.println("4. Extraction");
        System.out.println("5. Debit Card");
        System.out.println("6. Principal Menu");

        numValitor = sc.nextInt();

        switch (numValitor) {
            case 1:
                System.out.println("Status of the balance");
                try{
                    if(user.getAccounts()==null){
                        throw new ExceptApp("Ustede no posee cuentas");
                    }else{
                        for( BankAccount account : user.getAccounts()){
                            System.out.println(account.getCbu()+"-- "+ account.getBalance());
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                bankAccountService.actualBalance(user.getAccounts().get(0));

                break;

            case 2:
                System.out.println("Deposit ");
                // System.out.println("Cuanto quiere depositar");
                // int dep1= sc.nextInt();
                bankAccountService.deposit(user.getAccounts().get(0));


                break;

             /*   System.out.println("cuenta propia o cuenta de terceros");
                int num= sc.nextInt();
                switch (num) {
                    case 1:
                    break;
                    case 2:
                }
                break;*/
            case 3:
                System.out.println("Transaction");
                bankAccountService.transfer(user.getAccounts().get(0), customers);
                for( BankAccount account : user.getAccounts()){
                    System.out.println(account.getCbu()+"-- "+ account.getBalance());
                }
                break;
            case 4:
                System.out.println("Extraction");
                bankAccountService.extraction(user.getAccounts().get(0));

                break;
            case 5:
                System.out.println("Debit Card");
                bankAccount.setDebit(debitCardService.createDebitCard(customers));
                break;
            case 6:
                System.out.println("Return to previous menu");
                menuPrincipal();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return numValitor;

    }

    public List<User> inicialiarDatos() {

        List <User> customers = new ArrayList<User>();

        User user1 = new User("Veronica", "Castro", "verito@gmail.com", 89765456, 30, "verito", "1234");
        User user2 = new User("Pepita", "Oña", "chaquichaqui@gmail.com", 93872908, 20, "deoro", "3456");
        User user3 = new User("Luquitas", "Castro ", "luquiluqui@gmail.com", 23456123, 21, "lady", "5696");
        User user4 = new User("Makki", "Tranqui", "makkitranui@gmail.com", 87654312, 18, "makki10", "5236");
//		User user5 = new User("Yukki", "Revoltoso", "yukkirevoltoso@gmail.com", 19876789, 22, "yukki10", "3256");
//		User user6 = new User("Eiko", "Arcentales", "eikoarcentales@gmail.com", 12345678, 24, "eiko99", "5844");
//		User user7 = new User("Kalel", "Vega", "kalelvega@gmail.com", 89765456, 30, "kalel01", "2364");
//
//		User user8 = new User("Jeronimo", "Robbins", "jrobins@gmail.com", 98987612, 31, "Rojan", "1234");
//		User user9 = new User("Cleotilde", "Delramos", "cleocleo@gmail.com", 12345678, 40, "Roca", "3456");
//		User user10 = new User("Zeus", "Oña", "zeusona@gmail.com", 12121234, 20, "deOro", "1314");

        customers.add(user1);
        customers.add(user2);
        customers.add(user3);
        customers.add(user4);

        user1.getAccounts().add(new BankAccount(user1));
        user2.getAccounts().add(new BankAccount(user2));
        user3.getAccounts().add(new BankAccount(user3));
        user4.getAccounts().add(new BankAccount(user4));

        return customers;
    };
}
