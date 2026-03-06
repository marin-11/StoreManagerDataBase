import backend.Database;
import backend.repositories.InventoryRepo;
import backend.repositories.OrderRepo;
import backend.services.InventoryService;
import ui.Gui;
import ui.UIController;
import backend.services.OrderService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
Initializes everything
UI
    GUI (Window management wrapper) -> UIController (Navigation) -> Windows (What we see)
Backend
    Service (used in UIController) -> Repositories (Queries the db) -> DB (Handles the DB connection)
 */
public class App {
    public static void run() throws SQLException {
        String url = "jdbc:sqlite:/Users/ethanmarin/IdeaProjects/StoreManagerDataBase/manager.db";

        try (Database db = new Database(url)) {

            db.connect();
            Connection conn = db.getConnection();

            conn.createStatement().execute("PRAGMA foreign_keys = ON;");

            // Repositories
            InventoryRepo inventoryRepo = new InventoryRepo(conn);
            OrderRepo orderRepo = new OrderRepo(conn);

            // Services
            InventoryService inventoryService = new InventoryService(inventoryRepo);
            OrderService orderService = new OrderService(orderRepo);

            // GUI
            Gui gui = new Gui();
            gui.start();
            UIController ui = new UIController(gui, inventoryService, orderService);
            ui.showMainMenu();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}