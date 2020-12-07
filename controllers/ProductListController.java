package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.CurrencyTableCell;
import shop.ProductIsAvailableTableCell;
import shop.entities.Product;

import java.math.BigDecimal;

public class ProductListController {
    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, String> brandCol;

    @FXML
    private TableColumn<Product, String> sizeCol;

    @FXML
    private TableColumn<Product, String> colorCol;

    @FXML
    private TableColumn<Product, BigDecimal> priceCol;

    @FXML
    private TableColumn<Product, Boolean> isAvailableCol;

    @FXML
    private Button editBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Produtos");

        try {
            stage.setScene(new Scene(FXMLLoader.load(ProductListController.class.getResource("../views/ProductList.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os produtos. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Prepare table
         */
        this.idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        this.brandCol.setCellValueFactory(
                new PropertyValueFactory<>("brand")
        );
        this.sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size")
        );
        this.colorCol.setCellValueFactory(
                new PropertyValueFactory<>("color")
        );
        this.priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        this.isAvailableCol.setCellValueFactory(
                new PropertyValueFactory<>("isAvailable")
        );

        this.priceCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );
        this.isAvailableCol.setCellFactory(
                col -> new ProductIsAvailableTableCell<>()
        );

        this.table.setPlaceholder(new Label("Não há produtos para exibir"));
        this.table.setItems(App.getInstance().getProducts());

        /*
         * Bind properties
         */
        this.editBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openProductNew() {
        ProductNewController.open();
    }

    public void openProductEdit() {
        ProductEditController.open(this.table.getSelectionModel().getSelectedItem());
    }
}