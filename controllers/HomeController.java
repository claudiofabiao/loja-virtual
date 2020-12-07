package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.CurrencyTableCell;
import shop.entities.Cart;
import shop.entities.Customer;
import shop.entities.Product;

import java.math.BigDecimal;

public class HomeController {
    private Customer currentCustomer;
    private Cart cart;

    @FXML
    private Parent root;

    @FXML
    private Text welcomeText;

    @FXML
    private Text customerEmailText;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, BigDecimal> priceCol;

    @FXML
    private Button openProductDetailBtn;

    public static void open(Customer currentCustomer) {
        FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource("../views/Home.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Loja virtual");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar entrar. Contate o suporte.");

            alert.show();

            return;
        }

        ((HomeController)loader.getController()).setData(currentCustomer);

        stage.show();
    }

    public void setData(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
        this.cart = new Cart();

        /*
         * Filter product list
         */
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Product product: App.getInstance().getProducts()) {
            if (product.getIsAvailable()) {
                products.add(product);
            }
        }

        /*
         * Prepare table
         */
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        priceCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );

        table.setPlaceholder(new Label("Não há produtos para exibir"));
        table.setItems(products);

        /*
         * Bind properties
         */
        this.welcomeText.textProperty().bind(Bindings.concat("Olá, ", this.currentCustomer.fullNameProperty()));
        this.customerEmailText.textProperty().bind(this.currentCustomer.emailProperty());
        this.openProductDetailBtn.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openProductDetail() {
        ProductDetailController.open(this.cart, this.table.getSelectionModel().getSelectedItem());
    }

    public void openCart() {
        CartController.open(this.currentCustomer, this.cart);
    }

    public void openOrderList() {
        CustomerOrderListController.open(this.currentCustomer.getOrders());
    }

    public void openAddressList() {
        CustomerAddressListController.open(this.currentCustomer.getAddresses());
    }

    public void openCustomerEdit() {
        CustomerEditController.open("Editar meus dados", "Salvar meus dados", this.currentCustomer);
    }

    public void signOut() {
        this.root.getScene().getWindow().hide();
        CustomerSignInController.open();
    }
}