package ui;

import backend.services.InventoryService;
import backend.services.OrderService;
import ui.windows.*;
import com.googlecode.lanterna.gui2.Window;


import java.io.IOException;

public class UIController {

    private final Gui gui;
    private final InventoryService inventoryService;
    private final OrderService orderService;

    public UIController(Gui gui, InventoryService inventoryService, OrderService orderService) {
        this.gui = gui;
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    public void showMainMenu() {
        gui.show(new MainWindow(this));
    }

    public void showInventoryPage() {
        gui.show(new InventoryWindow(this, inventoryService));
    }

    public void showCreateOrderPage() {
        gui.show(new CreateOrderWindow(this, orderService));
    }

    public void showDeleteOrderPage() {
        gui.show(new DeleteOrderWindow(this, orderService));
    }

    public void switchWindow(Window current, Window next) throws IOException {
        gui.close(current);
        gui.show(next);
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void closeApp() {
        gui.close();
    }
}