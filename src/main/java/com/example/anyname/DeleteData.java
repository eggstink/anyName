package com.example.anyname;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM users WHERE id = ?"
             )){
            int id = 1;
            statement.setInt(1,id);

            int rows = statement.executeUpdate();
            System.out.println("Rows deleted: " + rows);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

