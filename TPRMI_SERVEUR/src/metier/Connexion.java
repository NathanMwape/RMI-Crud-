package metier;

import java.sql.Connection;

public class Connexion {
    private static Connection connection;

    public static Connection getConneXion() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/esisdb", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
