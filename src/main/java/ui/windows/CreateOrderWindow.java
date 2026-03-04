package ui.windows;
import backend.services.OrderService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import ui.UIController;
import java.util.List;
import java.sql.*;


public class CreateOrderWindow extends BasicWindow {

    private final UIController ui;
    private final OrderService orderService;

    public CreateOrderWindow(UIController ui, OrderService orderService) {
        super("Create Order");

        this.ui = ui;
        this.orderService = orderService;

        setHints(List.of(Hint.CENTERED));
        setComponent(build());
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        Label itemLabel = new Label("Item ID:");
        TextBox itemBox = new TextBox();

        Label userLabel = new Label("User ID:");
        TextBox userBox = new TextBox();

        Label amountLabel = new Label("Amount:");
        TextBox amountBox = new TextBox();

        Button submit = new Button("Submit Order", () -> {

            String itemText = itemBox.getText();
            String userText = userBox.getText();
            String amountText = amountBox.getText();

            if (itemText.isBlank() || userText.isBlank() || amountText.isBlank()) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "All fields must be filled."
                );
                return;
            }

            try {
                int itemId = Integer.parseInt(itemText);
                int userId = Integer.parseInt(userText);
                int amount = Integer.parseInt(amountText);

                orderService.createOrder(itemId, userId, amount);

                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Success",
                        "Order created successfully!"
                );

                itemBox.setText("");
                userBox.setText("");
                amountBox.setText("");

            } catch (NumberFormatException e) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "IDs and amount must be numbers."
                );
            }
        });

        Button back = new Button("Back", () ->
                ui.switchWindow(this, new MainWindow(ui))
        );

        panel.addComponent(itemLabel);
        panel.addComponent(itemBox);
        panel.addComponent(userLabel);
        panel.addComponent(userBox);
        panel.addComponent(amountLabel);
        panel.addComponent(amountBox);
        panel.addComponent(submit);
        panel.addComponent(back);

        return panel;
    }
}