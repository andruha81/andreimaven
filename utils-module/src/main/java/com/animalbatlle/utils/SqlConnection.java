package com.animalbatlle.utils;

import com.animalbattle.entities.Animal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/andreimaven?serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static String query;

    private SqlConnection() {
    }

    public static List<Animal> getFighters() throws SQLException {

        List<Animal> fighters = new ArrayList<>();

        query = "select * from fighters";
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                fighters.add(CreateFighter.createFighter(resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getString("name"),
                        resultSet.getInt("force"),
                        resultSet.getInt("agility")));
            }
        }
        return fighters;
    }

    public static void saveResults(Map<Animal, Integer> championshipTable) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            query = "delete from result_table";
            statement.executeUpdate(query);
            for (Map.Entry<Animal, Integer> result : championshipTable.entrySet()) {
                query = String.format("insert into result_table (fighter_id, won) values (%d, %d)", result.getKey().getId(), result.getValue());
                statement.executeUpdate(query);
            }
        }
    }

    public static void serialiseFighter(Object fighter) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into serialized_fighter (fighter) values (?)")) {
            query = "delete from serialized_fighter";
            statement.executeUpdate(query);
            preparedStatement.setObject(1, fighter);
            preparedStatement.executeUpdate();
        }
    }

    public static Object deserializeFighter() throws SQLException, IOException, ClassNotFoundException {
        query = "select * from serialized_fighter";
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            resultSet.next();
            byte[] buf = resultSet.getBytes(1);
            ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            objectIn.close();
            return objectIn.readObject();
        }
    }
}
