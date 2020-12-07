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
import shop.entities.Customer;

public class CustomerEditController {
    private Customer customer;

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
    private Button saveBtn;

    public static void open(String title, String btnLabel, Customer customer) {
        FXMLLoader loader = new FXMLLoader(CustomerEditController.class.getResource("../views/CustomerEdit.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar editar o cliente. Contate o suporte.");

            alert.show();

            return;
        }

        ((CustomerEditController)loader.getController()).setData(btnLabel, customer);

        stage.show();
    }

    public void setData(String btnLabel, Customer customer) {
        this.customer = customer;

        /*
         * Set button label
         */
        this.saveBtn.setText(btnLabel);

        /*
         * Fill fields
         */
        this.idField.setText(customer.getId());
        this.nameField.setText(customer.getName());
        this.surnameField.setText(customer.getSurname());
        this.phoneField.setText(customer.getPhone());
        this.emailField.setText(customer.getEmail());
        this.passwordField.setText(customer.getPassword());

        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.idField.getText().trim().isEmpty() || this.nameField.getText().trim().isEmpty() || this.surnameField.getText().trim().isEmpty() || this.phoneField.getText().trim().isEmpty() || this.emailField.getText().trim().isEmpty() || this.passwordField.getText().trim().isEmpty(), this.idField.textProperty(), this.nameField.textProperty(), this.surnameField.textProperty(), this.phoneField.textProperty(), this.emailField.textProperty(), this.passwordField.textProperty()));
    }

    public void save() {
        this.customer.setName(this.nameField.getText().trim());
        this.customer.setSurname(this.surnameField.getText().trim());
        this.customer.setPhone(this.phoneField.getText().trim());
        this.customer.setEmail(this.emailField.getText().trim());
        this.customer.setPassword(this.passwordField.getText().trim());

        this.root.getScene().getWindow().hide();
    }
}
