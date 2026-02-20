package backend.repositories;

import models.Inventory;
import models.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepo {

    private final Connection conn;

    public InventoryRepo(Connection conn) {
        this.conn = conn;
    }

    public List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();

        String sql = "SELECT * FROM Inventory";

        try (PreparedStatement sql1 = conn.prepareStatement(sql);
             ResultSet rs = sql1.executeQuery()) {

            while (rs.next()) {
                items.add(new Inventory(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getDouble("item_price"),
                        rs.getInt("item_stock"),
                        rs.getDouble("discount")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        return questions;
    }
}