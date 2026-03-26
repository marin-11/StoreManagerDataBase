package ui.windows;

import backend.services.InventoryService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import models.Inventory;
import ui.UIController;

import java.io.IOException;
import java.sql.*;

import java.util.List;

public class EditInventoryWindow extends BasicWindow {

    public EditInventoryWindow(UIController ui, InventoryService service, Inventory item) {

        super("Edit Item");

        setHints(List.of(Hint.CENTERED));

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        TextBox nameBox = new TextBox(item.getName());
        TextBox priceBox = new TextBox(String.valueOf(item.getPrice()));
        TextBox stockBox = new TextBox(String.valueOf(item.getStock()));
        TextBox discountBox = new TextBox(String.valueOf(item.getDiscount()));

        panel.addComponent(new Label("Name:"));
        panel.addComponent(nameBox);

        panel.addComponent(new Label("Price:"));
        panel.addComponent(priceBox);

        panel.addComponent(new Label("Stock:"));
        panel.addComponent(stockBox);

        panel.addComponent(new Label("Discount:"));
        panel.addComponent(discountBox);

        panel.addComponent(new Button("Save", () -> {

            try {
                String name = nameBox.getText();
                double price = Double.parseDouble(priceBox.getText());
                int stock = Integer.parseInt(stockBox.getText());
                double discount = Double.parseDouble(discountBox.getText());

                service.updateItem(item.getItemId(), name, price, stock, discount);

                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Success",
                        "Item updated!"
                );

                ui.switchWindow(this, new InventoryWindow(ui, service));

            } catch (Exception e) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "Invalid input!"
                );
            }
        }));

        panel.addComponent(new Button("Back",
                () -> {
                    try {
                        ui.switchWindow(this, new InventoryWindow(ui, service));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        ));

        setComponent(panel);
    }
}