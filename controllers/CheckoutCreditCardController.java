package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.entities.*;

public class CheckoutCreditCardController {
    private Customer currentCustomer;
    private Cart cart;
    private Address address;

    @FXML
    private Parent root;

    @FXML
    private TextField holderNameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField expirationDateField;

    @FXML
    private TextField securityCodeField;

    @FXML
    private Button checkoutBtn;

    public static void open(Customer currentCustomer, Cart cart, Address address) {
        FXMLLoader loader = new FXMLLoader(CheckoutCreditCardController.class.getResource("../views/CheckoutCreditCard.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inserir dados para pagamento");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar exibir esta tela. Contate o suporte.");

            alert.show();

            return;
        }

        ((CheckoutCreditCardController)loader.getController()).setData(currentCustomer, cart, address);

        stage.show();
    }

    public void setData(Customer currentCustomer, Cart cart, Address selectedAddress) {
        this.currentCustomer = currentCustomer;
        this.cart = cart;
        this.address = selectedAddress;

        /*
         * Bind properties
         */
        this.checkoutBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.holderNameField.getText().trim().isEmpty() || this.numberField.getText().trim().isEmpty() || this.expirationDateField.getText().trim().isEmpty() || this.securityCodeField.getText().trim().isEmpty(), this.holderNameField.textProperty(), this.numberField.textProperty(), this.expirationDateField.textProperty(), this.securityCodeField.textProperty()));
    }

    public void checkout() {
        ObservableList<OrderItem> items = FXCollections.observableArrayList();

        for (CartItem item: this.cart.getItems()) {
            items.add(new OrderItem(item.getProduct(), item.getQuantity()));
        }

        Order order = new Order(
                (App.getInstance().getOrders().size() + 1),
                this.currentCustomer,
                this.address,
                new CreditCard(this.holderNameField.getText().trim(), this.numberField.getText().trim(), this.expirationDateField.getText().trim(), this.securityCodeField.getText().trim()),
                items
        );

        App.getInstance().getOrders().add(order);
        this.currentCustomer.getOrders().add(order);

        this.root.getScene().getWindow().hide();
        CustomerOrderListController.open(this.currentCustomer.getOrders());
    }
}
