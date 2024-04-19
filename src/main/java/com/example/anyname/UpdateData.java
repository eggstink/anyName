package com.example.anyname;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET name=? WHERE id=?"
             )) {
            String name = "Rawr";
            int id = 1;
            statement.setString(1,name);
            statement.setInt(2,id);
            int rows = statement.executeUpdate();
            System.out.println("Rows Inserted: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

