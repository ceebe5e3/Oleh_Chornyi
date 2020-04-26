package ua.nure.chornyi.SummaryTask4.db;

import org.junit.BeforeClass;

import java.sql.*;


public class DBManagerTest {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    @BeforeClass
    public static void testConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/provider", "root", "1111");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ClassNotFoundException | SQLException");
        }
    }
}