package pl.bootcamp10.Java.db;

import pl.bootcamp10.Java.model.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
