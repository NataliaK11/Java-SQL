package pl.bootcamp10.Java.db;
import org.apache.commons.codec.digest.DigestUtils;
import pl.bootcamp10.Java.model.Car;
import pl.bootcamp10.Java.model.CarType;
import pl.bootcamp10.Java.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CarRepository {
    public static Connection connection=null;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");  //tworzy obiekt bez referencji
            CarRepository.connection = DriverManager.getConnection("jdbc:mysql://localhost/login?user=root&password=");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Zepsuło się łączenie do bazy");
            System.exit(0);
        }
    }

    public static void addCar(Car car){

        String sql = "INSERT INTO `tcar`(`brand`, `model`, `year`, `type`) VALUES (?, ?, ? ,?)";

        try {
            PreparedStatement ps = CarRepository.connection.prepareStatement(sql);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setObject(4, car.getCarType());
            ps.executeUpdate();
            System.out.println("Dodano");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void showAllCars() {
        List<Car> carList=new ArrayList<>();

        String sql = "SELECT * FROM `tcar`";
        try {

            PreparedStatement ps = CarRepository.connection.prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();


while (resultSet.next()) {
    Car car = new Car();
    car.setId(resultSet.getInt("id"));
    car.setBrand(resultSet.getString("brand"));
    car.setModel(resultSet.getString("model"));
    car.setYear(resultSet.getInt("year"));
    car.setCarType(resultSet.getString("type"));
    carList.add(car);
    resultSet.next();
}


        }catch(SQLException e){
            e.printStackTrace();
        }
        Iterator <Car> iterator=carList.iterator();
        if(iterator.hasNext())
            System.out.println(iterator.next().toString());

    }

    public static Car reservationCar(){
        Scanner input=new Scanner(System.in);
        System.out.println("podaj markę samochodu");
        String brand=input.nextLine();
        System.out.println("podaj model samochodu");
        String model=input.nextLine();
        System.out.println("Podaj typ samochodu");
        String carType=input.nextLine();
        Car car=new Car(brand,model,carType);

            return car;
    }


    public static boolean checkIfCarExist(Car car) {
        String sql = "SELECT * FROM tcar WHERE brand=?";


        try {
            PreparedStatement ps = CarRepository.connection.prepareStatement(sql);
            ps.setString(1, car.getBrand());

            ResultSet resultSet = ps.executeQuery();
            while (true){
                if(resultSet.getString("model").equals(car.getModel())&&resultSet.getString("type").equals(car.getCarType()))
            return true;
                else return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
