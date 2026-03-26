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

        String sql = "SELECT * FROM inventory";

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

    public void updateItem(int itemId, String name, double price, int stock, double discount) {

        String sql = """
                    UPDATE inventory
                    SET item_name = ?, item_price = ?, item_stock = ?, discount = ?
                    WHERE item_id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stock);
            stmt.setDouble(4, discount);
            stmt.setInt(5, itemId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}