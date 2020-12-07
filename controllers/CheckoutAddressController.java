package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Address;
import shop.entities.Cart;
import shop.entities.Customer;

public class CheckoutAddressController {
    private Customer currentCustomer;
    private Cart cart;

    @FXML
    private Parent root;

    @FXML
    private TableView<Address> table;

    @FXML
    private TableColumn<Address, String> nameCol;

    @FXML
    private TableColumn<Address, String> zipCodeCol;

    @FXML
    private TableColumn<Address, String> streetCol;

    @FXML
    private TableColumn<Address, String> numberCol;

    @FXML
    private TableColumn<Address, String> cityCol;

    @FXML
    private TableColumn<Address, String> stateCol;

    @FXML
    private Button selectBtn;

    public static void open(Customer currentCustomer, Cart cart) {
        FXMLLoader loader = new FXMLLoader(CheckoutAddressController.class.getResource("../views/CheckoutAddress.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Selecionar endereço de entrega");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os seus endereços. Contate o suporte.");

            alert.show();

            return;
        }

        ((CheckoutAddressController)loader.getController()).setData(currentCustomer, cart);

        stage.show();
    }

    public void setData(Customer currentCustomer, Cart cart) {
        this.currentCustomer = currentCustomer;
        this.cart = cart;

        /*
         * Prepare table
         */
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        this.zipCodeCol.setCellValueFactory(
                new PropertyValueFactory<>("zipCode")
        );
        this.streetCol.setCellValueFactory(
                new PropertyValueFactory<>("street")
        );
        this.numberCol.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        this.cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city")
        );
        this.stateCol.setCellValueFactory(
                new PropertyValueFactory<>("state")
        );

        this.table.setPlaceholder(new Label("Não há endereços para exibir"));
        this.table.setItems(this.currentCustomer.getAddresses());

        /*
         * Bind properties
         */
        this.selectBtn.disableProperty().bind(Bindings.isNull(this.table.getSelectionModel().selectedItemProperty()));
    }

    public void next() {
        this.root.getScene().getWindow().hide();
        CheckoutCreditCardController.open(this.currentCustomer, this.cart, this.table.getSelectionModel().getSelectedItem());
    }
}