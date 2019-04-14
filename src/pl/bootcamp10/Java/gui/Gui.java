package pl.bootcamp10.Java.gui;

import pl.bootcamp10.Java.db.UserRepository;
import pl.bootcamp10.Java.model.User;
import java.util.Scanner;

public class Gui {

    private static int helloMenu() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            if (choice.equals(1)|| choice.equals(2) || choice.equals(3)) {
                return Integer.parseInt(choice);
            }

        }
    }

    private static User register() {
        while (true) {
            System.out.println("Login: ");
            Scanner input = new Scanner(System.in);
            String login = input.nextLine();
            System.out.println("Passwodr: ");
            String password = input.nextLine();
            System.out.println("Repeat password");
            String password2 = input.nextLine();

            if (password.equals(password2)) {
                User user = new User(login, password);
                return user;
            } else System.out.println("Password incorects!");
        }
    }

    private static User login() {
        System.out.println("Login: ");
        Scanner input = new Scanner(System.in);
        String login = input.nextLine();
        System.out.println("Passwodr: ");
        String password = input.nextLine();

        User user = new User(login, password);
        return user;
    }

    public static void appController() {
        while (true) {
            switch (helloMenu()) {
                case 1: {
                    User user= register();
                    if(!UserRepository.checkIfUserExist(user)){
                        UserRepository.saveUser(user);
                    }

                }
                break;
                case 2: {
                    User user = login();
                    boolean isOk = UserRepository.authenticateUser(user);
                    if (isOk) {
                        System.out.println("Zalogowano");
                    } else System.out.println("Błąd logowania");

                }break;
                case 3: {
                    return;
                }
            }
        }
    }


}
