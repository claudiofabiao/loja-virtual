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
import shop.entities.Administrator;

public class AdminSignInController {
    @FXML
    private Parent root;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Entrar");

        try {
            stage.setScene(new Scene(FXMLLoader.load(ProductListController.class.getResource("../views/AdminSignIn.fxml"))));
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
        this.signInBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.emailField.getText().trim().isEmpty() || this.passwordField.getText().trim().isEmpty(), this.emailField.textProperty(), this.passwordField.textProperty()));
    }

    public void signIn() {
        Administrator foundAdministrator = Administrator.findByCredentials(this.emailField.getText(), this.passwordField.getText());

        if (foundAdministrator == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Credenciais incorretas");
            alert.setHeaderText("Credenciais incorretas");
            alert.setContentText("As credenciais inseridas estão incorretas. Verifique o e-mail e/ou senha e tente novamente.");

            alert.show();
        } else {
            this.root.getScene().getWindow().hide();
            DashboardController.open(foundAdministrator);
        }
    }

    public void signInAsCustomer() {
        this.root.getScene().getWindow().hide();
        CustomerSignInController.open();
    }
}
