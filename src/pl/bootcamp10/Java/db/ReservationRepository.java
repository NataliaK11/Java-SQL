package pl.bootcamp10.Java.db;

import pl.bootcamp10.Java.model.Car;
import pl.bootcamp10.Java.model.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ReservationRepository {
    public static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");  //tworzy obiekt bez referencji
            UserRepository.connection = DriverManager.getConnection("jdbc:mysql://localhost/login?user=root&password=");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Zepsuło się łączenie do bazy");
            System.exit(0);
        }
    }

    public static void addReservation(Reservation reservation) {
        String sql = "INSERT INTO treservation (startDate, endDate, carId) VALUES (?,?,?)";

        try {
            PreparedStatement ps = ReservationRepository.connection.prepareStatement(sql);
            //ps.setDate(1,reservation.getStartDate());
            //ps.setDate(2,reservation.getEndDate());
            ps.setInt(3, reservation.getCarId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void enterYourReservation() {
//        Scanner input = new Scanner(System.in);
//        while (true) {
//            System.out.println("Podaj datę rozpoczęcia rezerwacji");
//            Date startDate;
//            System.out.println("Podaj datę zakońćzenia");
//            Date endDate;
//            Car car = CarRepository.enterCarDetails();
//            if (CarRepository.checkIfCarExist(car)) {
//                int carIdFromDatabase = CarRepository.getCarIdFromDatabase(car);
//                Reservation reservation = new Reservation(startDate, endDate, carIdFromDatabase);
//                addReservation(reservation);
//            } else System.out.println("takie auto nie istnieje w naszej bazie");
//        }
//    }
    public static void showAllReservations(){

    }
}