package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {

    public Connection connect() throws Exception{
//        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/resume?" + "useTimezone=true&"
                + "serverTimezone=UTC";
        String username = "root";
        String password = "Kanan20012020";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
