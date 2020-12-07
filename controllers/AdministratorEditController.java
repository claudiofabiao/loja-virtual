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

public class AdministratorEditController {
    private Administrator administrator;

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

    public static void open(Administrator administrator) {
        FXMLLoader loader = new FXMLLoader(AdministratorEditController.class.getResource("../views/AdministratorEdit.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Editar administrador");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar editar o administrador. Contate o suporte.");

            alert.show();

            return;
        }

        ((AdministratorEditController)loader.getController()).setData(administrator);

        stage.show();
    }

    public void setData(Administrator administrator) {
        this.administrator = administrator;

        /*
         * Fill fields
         */
        this.nameField.setText(administrator.getName());
        this.surnameField.setText(administrator.getSurname());
        this.emailField.setText(administrator.getEmail());
        this.passwordField.setText(administrator.getPassword());

        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.nameField.getText().trim().isEmpty() || this.surnameField.getText().trim().isEmpty() || this.emailField.getText().trim().isEmpty() || this.passwordField.getText().trim().isEmpty(), this.nameField.textProperty(), this.surnameField.textProperty(), this.emailField.textProperty(), this.passwordField.textProperty()));
    }

    public void save() {
        this.administrator.setName(this.nameField.getText().trim());
        this.administrator.setSurname(this.surnameField.getText().trim());
        this.administrator.setEmail(this.emailField.getText().trim());
        this.administrator.setPassword(this.passwordField.getText().trim());

        this.root.getScene().getWindow().hide();
    }
}
