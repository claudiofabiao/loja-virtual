package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Product;

public class ProductEditController {
    private Product product;

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

    public static void open(Product product) {
        FXMLLoader loader = new FXMLLoader(ProductEditController.class.getResource("../views/ProductEdit.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Editar produto");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar editar o produto. Contate o suporte.");

            alert.show();

            return;
        }

        ((ProductEditController)loader.getController()).setData(product);

        stage.show();
    }

    public void setData(Product product) {
        this.product = product;

        /*
         * Fill fields
         */
        this.idField.setText(product.getId());
        this.nameField.setText(product.getName());
        this.brandField.setText(product.getBrand());
        this.sizeField.setText(product.getSize());
        this.colorField.setText(product.getColor());
        this.priceField.setText(product.getPrice().toString());
        this.isAvailableField.setSelected(product.getIsAvailable());

        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.idField.getText().trim().isEmpty() || this.nameField.getText().trim().isEmpty() || this.brandField.getText().trim().isEmpty() || this.sizeField.getText().trim().isEmpty() || this.colorField.getText().trim().isEmpty() || this.priceField.getText().trim().isEmpty(), this.idField.textProperty(), this.nameField.textProperty(), this.brandField.textProperty(), this.sizeField.textProperty(), this.colorField.textProperty(), this.priceField.textProperty()));
    }

    public void save() {
        this.product.setName(this.nameField.getText().trim());
        this.product.setBrand(this.brandField.getText().trim());
        this.product.setSize(this.sizeField.getText().trim());
        this.product.setColor(this.colorField.getText().trim());
        this.product.setPrice(this.priceField.getText().trim());
        this.product.setIsAvailable(this.isAvailableField.isSelected());

        this.root.getScene().getWindow().hide();
    }
}
