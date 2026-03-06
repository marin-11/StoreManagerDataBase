package ui.windows;

import backend.services.OrderService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import ui.UIController;

import java.io.IOException;
import java.sql.*;

public class DeleteOrderWindow extends BasicWindow {

    public DeleteOrderWindow(UIController ui, OrderService service) {

        super("Cancel Order");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Label("Enter Order ID to cancel:"));

        TextBox orderIdBox = new TextBox();

        panel.addComponent(orderIdBox);

        panel.addComponent(new Button("Cancel Order", () -> {

            int orderId = Integer.parseInt(orderIdBox.getText());

            MessageDialogButton result =
                    MessageDialog.showMessageDialog(
                            getTextGUI(),
                            "Confirm Delete",
                            "Are you sure you want to cancel this order?",
                            MessageDialogButton.Yes,
                            MessageDialogButton.No
                    );

            if (result == MessageDialogButton.Yes) {
                service.deleteOrder(orderId);

                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Success",
                        "Order cancelled successfully",
                        MessageDialogButton.OK
                );

                try {
                    ui.switchWindow(this, new MainWindow(ui));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        panel.addComponent(new Button("Back", () -> {
            try {
                ui.switchWindow(this, new MainWindow(ui));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        setComponent(panel);
    }
}