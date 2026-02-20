package ui.windows;

import com.googlecode.lanterna.gui2.*;
import ui.UIController;
import com.googlecode.lanterna.gui2.BasicWindow;

public class CreateOrderWindow extends BasicWindow {

    public CreateOrderWindow(UIController controller) {
        super("Create Order");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Label("Order Creation Screen"));

        panel.addComponent(new Button("Back to Inventory", () -> {
            controller.showInventoryPage();
            this.close();
        }));

        setComponent(panel);
    }
}