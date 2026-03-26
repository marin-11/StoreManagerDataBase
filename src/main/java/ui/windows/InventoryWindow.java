package ui.windows;

import backend.services.InventoryService;
import com.googlecode.lanterna.gui2.*;
import models.Inventory;
import ui.UIController;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class InventoryWindow extends BasicWindow {

    public InventoryWindow(UIController controller, InventoryService service) {
        super("inventory");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        ActionListBox list = new ActionListBox();

        List<Inventory> items = service.getAllInventory();

        for (Inventory item : items) {
            list.addItem(
                    item.getName() + " | $" + item.getPrice() + " | Stock: " + item.getStock(),
                    () -> {
                        try {
                            controller.switchWindow(this,
                                    new EditInventoryWindow(controller, service, item)
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }

        panel.addComponent(list);

        panel.addComponent(new Button("Create Order", () -> {
            try {
                controller.switchWindow(this,
                        new CreateOrderWindow(controller, controller.getOrderService()));
            } catch (IOException e) {
//                throw new RuntimeException(e);
            }
        }));

        panel.addComponent(new Button("Back", () -> {
            try {
                controller.switchWindow(this,
                        new MainWindow(controller)
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        setComponent(panel);
    }
}