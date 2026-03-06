package backend.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepo {

    private final Connection conn;

    public OrderRepo(Connection conn) {
        this.conn = conn;
    }

    public void createOrder(int itemId, int userId, int amount) {
        String insertOrder = """
                    INSERT INTO orders (item_id, user_id, amount_ordered, date_ordered)
                    VALUES (?, ?, ?, date('now'))
                """;

        String updateStock = """
                    UPDATE inventory
                    SET current_stock = current_stock - ?
                    WHERE item_id = ?
                """;

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrder);
                 PreparedStatement stockStmt = conn.prepareStatement(updateStock)) {

                orderStmt.setInt(1, itemId);
                orderStmt.setInt(2, userId);
                orderStmt.setInt(3, amount);
                orderStmt.executeUpdate();

                stockStmt.setInt(1, amount);
                stockStmt.setInt(2, itemId);
                stockStmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOrder(int orderId) {

        String sql = """
        DELETE FROM orders WHERE order_id = ?
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

