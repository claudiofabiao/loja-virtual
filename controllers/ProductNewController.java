package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.entities.Product;

public class ProductNewController {
    @FXML
    private Parent root;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField priceField;

    @FXML
    private CheckBox isAvailableField;

    @FXML
    private Button saveBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Adicionar produto");

        try {
            stage.setScene(new Scene(FXMLLoader.load(ProductListController.class.getResource("../views/ProductNew.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar adicionar um novo produto. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.idField.getText().trim().isEmpty() || this.nameField.getText().trim().isEmpty() || this.brandField.getText().trim().isEmpty() || this.sizeField.getText().trim().isEmpty() || this.colorField.getText().trim().isEmpty() || this.priceField.getText().trim().isEmpty(), this.idField.textProperty(), this.nameField.textProperty(), this.brandField.textProperty(), this.sizeField.textProperty(), this.colorField.textProperty(), this.priceField.textProperty()));
    }

    public void save() {
        App.getInstance().getProducts().add(
                new Product(
                        this.idField.getText().trim(),
                        this.nameField.getText().trim(),
                        this.brandField.getText().trim(),
                        this.sizeField.getText().trim(),
                        this.colorField.getText().trim(),
                        this.priceField.getText().trim(),
                        this.isAvailableField.isSelected()
                )
        );

        this.root.getScene().getWindow().hide();
    }
}
