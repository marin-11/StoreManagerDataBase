package ui.windows;

import backend.services.InventoryService;
import com.googlecode.lanterna.gui2.*;
import ui.UIController;

public class InventoryWindow extends BasicWindow {

    public InventoryWindow(UIController controller, InventoryService inventoryService) {
        super("Inventory");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Label("Inventory Items Here"));

        panel.addComponent(new Button("Create Order", () -> {
            controller.showCreateOrderPage();
            this.close();
        }));

        panel.addComponent(new Button("Back", () -> {
            controller.showMainMenu();
            this.close();
        }));

        setComponent(panel);
    }
}