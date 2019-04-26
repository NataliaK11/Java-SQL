package pl.bootcamp10.Java;

import pl.bootcamp10.Java.db.CarRepository;
import pl.bootcamp10.Java.db.ReservationRepository;
import pl.bootcamp10.Java.db.UserRepository;
import pl.bootcamp10.Java.gui.Gui;
import pl.bootcamp10.Java.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository.connect();
        CarRepository.connect();
        ReservationRepository.connect();
       /* List<User> usersFromDatabase=UserRepository.getAllUsers();

        for(User user: usersFromDatabase){
            System.out.println(user.getId()+" - "+ user.getLogin()+" - "+user.getPassword());
        }*/

        Gui.appController();
    }
}
