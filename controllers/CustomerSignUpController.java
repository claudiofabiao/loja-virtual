package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.entities.Customer;

public class CustomerSignUpController {
    @FXML
    private Parent root;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Cadastrar");

        try {
            stage.setScene(new Scene(FXMLLoader.load(CustomerSignUpController.class.getResource("../views/CustomerSignUp.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar entrar. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Bind properties
         */
        this.signUpBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.idField.getText().trim().isEmpty() || this.nameField.getText().trim().isEmpty() || this.surnameField.getText().trim().isEmpty() || this.phoneField.getText().trim().isEmpty() || this.emailField.getText().trim().isEmpty() || this.passwordField.getText().trim().isEmpty(), this.idField.textProperty(), this.nameField.textProperty(), this.surnameField.textProperty(), this.phoneField.textProperty(), this.emailField.textProperty(), this.passwordField.textProperty()));
    }

    public void signUp() {
        Customer customer = new Customer(
                this.idField.getText().trim(),
                this.nameField.getText().trim(),
                this.surnameField.getText().trim(),
                this.phoneField.getText().trim(),
                this.emailField.getText().trim(),
                this.passwordField.getText().trim()
        );

        App.getInstance().getCustomers().add(customer);

        this.root.getScene().getWindow().hide();
        HomeController.open(customer);
    }
}
