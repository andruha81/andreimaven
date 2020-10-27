package com.animalbatlle.utils;

import com.animalbattle.entities.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/andreimaven";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "AndreiKhaustau1";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private SqlConnection() {
    }

    public static List<Animal> getFighters() throws SQLException {

        String querry = "select * from fighters";
        List<Animal> fighters = new ArrayList<>();

        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        System.out.println("64644644");
        statement = connection.createStatement();
        resultSet = statement.executeQuery(querry);

        while (resultSet.next()) {
            fighters.add(CreateFighter.createFighter(resultSet.getString("type")
                    , resultSet.getString("name")
                    , resultSet.getInt("force")
                    , resultSet.getInt("agility")));
        }
        return fighters;
    }
}
