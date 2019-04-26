package pl.bootcamp10.Java.db;

import org.apache.commons.codec.digest.DigestUtils;
import pl.bootcamp10.Java.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
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

    //String sql = "INSERT INTO tuser (login, pass) VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "');";
      /*  try {
            Statement s = UserRepository.connection.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    public static void saveUser(User user) {
        String sql = "INSERT INTO tuser (login, pass) VALUES (?, ?);";


        try {
            PreparedStatement ps = UserRepository.connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, DigestUtils.md5Hex(user.getPassword()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkIfUserExist(User user) {
        String sql = "SELECT * FROM tuser WHERE login=?";


        try {
            PreparedStatement ps = UserRepository.connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM tuser";


        try {
            PreparedStatement ps = UserRepository.connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("pass"));

                userList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userList;
    }

    public static boolean authenticateUser(User user) {
        String sql = "SELECT * FROM tuser WHERE login =?";

        try {
            PreparedStatement preparedStatement = UserRepository.connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String userPasswordFromDatabase=resultSet.getString("pass");
                return DigestUtils.md5Hex(user.getPassword()).equals(userPasswordFromDatabase);

            }else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean identificateAdmin(User user){
        String sql="SELECT * FROM tuser WHERE id=1";
        try {

            PreparedStatement ps = UserRepository.connection.prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()) {
                String adminNameFromDataBase = resultSet.getString("login");
                String adminPasswordFromDataBase = resultSet.getString("pass");
                if (user.getLogin().equals(adminNameFromDataBase) || adminPasswordFromDataBase.equals(DigestUtils.md5Hex(user.getPassword()))) {
                    return true;
                } else return false;
            }else return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

