package pl.bootcamp10.Java.gui;

import pl.bootcamp10.Java.db.CarRepository;
import pl.bootcamp10.Java.db.UserRepository;
import pl.bootcamp10.Java.model.Car;
import pl.bootcamp10.Java.model.CarType;
import pl.bootcamp10.Java.model.Reservation;
import pl.bootcamp10.Java.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Gui {

    private static int helloMenu() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            if (choice.equals("1")|| choice.equals("2") || choice.equals("3")) {
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
        System.out.println("Passwodrd: ");
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

                        if(UserRepository.identificateAdmin(user)){
                            System.out.println("Zalogowano jako admin");
                            while(true) {
                                switch (halloAdmin()) {
                                    case 1: {
                                        CarRepository.addCar(enterCarDetails());
                                    }
                                    break;
                                    case 2: {
                                        CarRepository.showAllCars();


                                    }
                                    break;
                                    case 3: {

                                    }
                                    break;
                                    case 4: {
                                        return;
                                    }

                                }
                            }

                        }else {
                            System.out.println("Zalogowano jako klient");
                            while (true) {
                                switch (halloCustomer()) {
                                    case 1: {

                                    }
                                    break;
                                    case 2: {
                                        CarRepository.showAllCars();

                                    }
                                    break;

                                    case 3: {
                                        return;
                                    }

                                }

                            }
                        }

                    } else System.out.println("Błąd logowania");

                }break;
                case 3: {
                    return;
                }
            }
        }
    }

    private static int halloAdmin() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. Dodaj samochód, 2. Wyświetl wszystkie samochody, 3. Wyświetl rezerwacje, 4.Wyjdź");


            String choice = input.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")||choice.equals("4")) {
                return Integer.parseInt(choice);
            }
        }
    }
    private static Car enterCarDetails(){
        Scanner input=new Scanner(System.in);
        System.out.println("Dodajemy samochód:");
        System.out.println("Podaj markę samochodu");
        String brand=input.nextLine();
        System.out.println("Podaj model samochodu");
        String model=input.nextLine();
        System.out.println("Podaj rok produkcji samochodu");
        int year=Integer.parseInt(input.nextLine());
        System.out.println("Podaj typ samochodu ('PASSENGER, VAN, OFFROAD)");
        String carType=input.nextLine();
        return new Car(brand,model,year,carType);

    }
    private static int halloCustomer(){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. Dodaj rezerwację, 2. Wyświetl wszystkie samochody, 3. Wyjdź");


            String choice = input.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                return Integer.parseInt(choice);
            }
        }
    }


//private static Reservation addReservation(){
 //       Scanner input=new Scanner(System.in);
 //   System.out.println("Podaj datę rozpoczęcia rezerwacji");
 //   System.out.println("Podaj datę zakońćzenia");
 //   Car car=CarRepository.reservationCar();
 //   if (CarRepository.checkIfCarExist(car)){

 //   }

//}


}
