package ui.windows;

import com.googlecode.lanterna.gui2.*;
import ui.UIController;

public class MainWindow extends BasicWindow {

    public MainWindow(UIController controller) {
        super("Main Menu");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Button("View Inventory", () -> {
            controller.showInventoryPage();
            this.close();
        }));

        panel.addComponent(new Button("Exit", controller::closeApp));

        setComponent(panel);
    }
}