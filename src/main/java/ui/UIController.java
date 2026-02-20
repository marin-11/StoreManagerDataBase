package ui;

import backend.repositories.InventoryRepo;
import backend.services.InventoryService;
import com.googlecode.lanterna.gui2.Window;
import ui.windows.MainWindow;
import ui.windows.InventoryWindow;
import ui.windows.CreateOrderWindow;
import ui.windows.MainWindow;

/*
Handles navigation
 */
public class UIController {

    private final Gui gui;
    private InventoryRepo inventoryRepo = null;

    public UIController(Gui gui, InventoryService inventoryService) {
        this.gui = gui;
        this.inventoryRepo = inventoryRepo;
    }

    public void showMainMenu() {
        gui.show(new MainWindow(this));
    }

    public void showAllInventoryPage() {
        gui.show(new CreateOrderWindow(this));
    }

    public void showInventoryPage() {
        gui.show(new InventoryWindow(this));
    }

    public void showCreateOrderPage() {
        gui.show(new CreateOrderWindow(this));
    }

    public void closeWindow(Window window) {
        window.close();
    }

    public void closeApp() {
        gui.close();
    }
}
