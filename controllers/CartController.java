package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import shop.CurrencyTableCell;
import shop.entities.Cart;
import shop.entities.CartItem;
import shop.entities.Customer;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class CartController {
    private Customer currentCustomer;
    private Cart cart;

    @FXML
    private Parent root;

    @FXML
    private TableView<CartItem> table;

    @FXML
    private TableColumn<CartItem, String> nameCol;

    @FXML
    private TableColumn<CartItem, Integer> quantityCol;

    @FXML
    private TableColumn<CartItem, BigDecimal> priceCol;

    @FXML
    private TableColumn<CartItem, BigDecimal> totalCol;

    @FXML
    private TextField totalField;

    public static void open(Customer currentCustomer, Cart cart) {
        FXMLLoader loader = new FXMLLoader(CartController.class.getResource("../views/Cart.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Carrinho");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os itens no carrinho. Contate o suporte.");

            alert.show();

            return;
        }

        ((CartController)loader.getController()).setData(currentCustomer, cart);

        stage.show();
    }

    public void setData(Customer currentCustomer, Cart cart) {
        this.currentCustomer = currentCustomer;
        this.cart = cart;

        /*
         * Prepare table
         */
        this.nameCol.setCellValueFactory(
                cellData -> cellData.getValue().getProduct().nameProperty()
        );
        this.quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        this.priceCol.setCellValueFactory(
                cellData -> cellData.getValue().getProduct().priceProperty()
        );
        this.totalCol.setCellValueFactory(
                new PropertyValueFactory<>("total")
        );

        this.quantityCol.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter())
        );
        this.priceCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );
        this.totalCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );

        this.table.setPlaceholder(new Label("Seu carrinho está vazio"));
        this.table.setItems(this.cart.getItems());

        /*
         * Bind properties
         */
        this.totalField.textProperty().bind(Bindings.createStringBinding(() -> NumberFormat.getCurrencyInstance().format(this.cart.getTotal()), this.cart.totalProperty()));
    }

    public void remove() {
        this.cart.getItems().remove(this.table.getSelectionModel().getSelectedItem());
    }

    public void next() {
        this.root.getScene().getWindow().hide();
        CheckoutAddressController.open(this.currentCustomer, this.cart);
    }
}