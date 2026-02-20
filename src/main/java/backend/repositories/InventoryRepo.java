package backend.repositories;
import models.Inventory;

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

        try (PreparedStatement sql = conn.prepareStatement(sql);
             ResultSet rs = sql.executeQuery()) {

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
}