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
import shop.entities.Administrator;

public class AdministratorNewController {
    @FXML
    private Parent root;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button saveBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Adicionar administrador");

        try {
            stage.setScene(new Scene(FXMLLoader.load(AdministratorNewController.class.getResource("../views/AdministratorNew.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar adicionar um novo administrador. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.nameField.getText().trim().isEmpty() || this.surnameField.getText().trim().isEmpty() || this.emailField.getText().trim().isEmpty() || this.passwordField.getText().trim().isEmpty(), this.nameField.textProperty(), this.surnameField.textProperty(), this.emailField.textProperty(), this.passwordField.textProperty()));
    }

    public void save() {
        App.getInstance().getAdministrators().add(
                new Administrator(
                        this.nameField.getText().trim(),
                        this.surnameField.getText().trim(),
                        this.emailField.getText().trim(),
                        this.passwordField.getText().trim()
                )
        );

        this.root.getScene().getWindow().hide();
    }
}
