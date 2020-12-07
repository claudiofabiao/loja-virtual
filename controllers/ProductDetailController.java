package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Cart;
import shop.entities.CartItem;
import shop.entities.Product;

import java.text.NumberFormat;

public class ProductDetailController {
    private Cart cart;
    private Product product;

    @FXML
    private Text nameText;

    @FXML
    private Text brandText;

    @FXML
    private Text sizeText;

    @FXML
    private Text colorText;

    @FXML
    private Text priceText;

    @FXML
    private Button addBtn;

    public static void open(Cart cart, Product product) {
        FXMLLoader loader = new FXMLLoader(ProductDetailController.class.getResource("../views/ProductDetail.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Detalhes do produto");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar exibir os detalhes produto. Contate o suporte.");

            alert.show();

            return;
        }

        ((ProductDetailController)loader.getController()).setData(cart, product);

        stage.show();
    }

    private void setData(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;

        /*
         * Bind properties
         */
        this.nameText.textProperty().bind(product.nameProperty());
        this.brandText.textProperty().bind(product.brandProperty());
        this.sizeText.textProperty().bind(product.sizeProperty());
        this.colorText.textProperty().bind(product.colorProperty());
        this.priceText.textProperty().bind(Bindings.createStringBinding(() -> NumberFormat.getCurrencyInstance().format(product.getPrice()), product.priceProperty()));
        this.addBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.cart.has(product), this.cart.itemsProperty()));
    }

    public void add() {
        this.cart.getItems().add(new CartItem(this.product));
    }
}
