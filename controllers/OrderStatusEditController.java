package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.OrderStatusListCell;
import shop.entities.Order;

public class OrderStatusEditController {
    private Order order;

    @FXML
    private Parent root;

    @FXML
    private ComboBox<Integer> statusField;

    public static void open(Order order) {
        FXMLLoader loader = new FXMLLoader(OrderItemListController.class.getResource("../views/OrderStatusEdit.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Editar situação do pedido");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar editar a situação do pedido. Contate o suporte.");

            alert.show();

            return;
        }

        ((OrderStatusEditController)loader.getController()).setData(order);

        stage.show();
    }

    public void setData(Order order) {
        this.order = order;

        /*
         * Set options
         */
        this.statusField.getItems().addAll(
                Order.CANCELED_STATUS,
                Order.AWAITING_PAYMENT_CONFIRMATION_STATUS,
                Order.SEPARATING_STATUS,
                Order.SENT_STATUS,
                Order.DELIVERED_STATUS
        );

        this.statusField.setButtonCell(
                new OrderStatusListCell()
        );
        this.statusField.setCellFactory(
                col -> new OrderStatusListCell()
        );

        /*
         * Select current status
         */
        this.statusField.getSelectionModel().select(order.getStatus());
    }

    public void save() {
        this.order.setStatus(this.statusField.getSelectionModel().getSelectedItem());
        this.root.getScene().getWindow().hide();
    }
}
