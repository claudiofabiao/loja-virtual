package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Address;

public class CustomerAddressNewController {
    private ObservableList<Address> addresses;

    @FXML
    private Parent root;

    @FXML
    private TextField nameField;

    @FXML
    private TextField zipCodeField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField cityField;

    @FXML
    private ChoiceBox<String> stateField;

    @FXML
    private Button saveBtn;

    public static void open(ObservableList<Address> addresses) {
        FXMLLoader loader = new FXMLLoader(CustomerEditController.class.getResource("../views/CustomerAddressNew.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Adicionar endereço do cliente");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar adicionar um novo endereço do cliente. Contate o suporte.");

            alert.show();

            return;
        }

        ((CustomerAddressNewController)loader.getController()).setData(addresses);

        stage.show();
    }

    public void setData(ObservableList<Address> addresses) {
        this.addresses = addresses;

        /*
         * Prepare fields
         */
        this.stateField.getItems().addAll(
                "Acre",
                "Alagoas",
                "Amapá",
                "Amazonas",
                "Bahia",
                "Ceará",
                "Distrito Federal",
                "Espírito Santo",
                "Goiás",
                "Maranhão",
                "Mato Grosso",
                "Mato Grosso do Sul",
                "Minas Gerais",
                "Pará",
                "Paraíba",
                "Paraná",
                "Pernambuco",
                "Piauí",
                "Rio de Janeiro",
                "Rio Grande do Norte",
                "Rio Grande do Sul",
                "Rondônia",
                "Roraima",
                "Santa Catarina",
                "São Paulo",
                "Sergipe",
                "Tocantins"
        );

        /*
         * Bind properties
         */
        this.saveBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> this.nameField.getText().trim().isEmpty() || this.zipCodeField.getText().trim().isEmpty() || this.streetField.getText().trim().isEmpty() || this.numberField.getText().trim().isEmpty() || this.cityField.getText().trim().isEmpty() || this.stateField.getSelectionModel().getSelectedItem() == null, this.nameField.textProperty(), this.zipCodeField.textProperty(), this.streetField.textProperty(), this.numberField.textProperty(), this.cityField.textProperty(), this.stateField.getSelectionModel().selectedItemProperty()));
    }

    public void save() {
        this.addresses.add(
                new Address(
                        this.nameField.getText().trim(),
                        this.zipCodeField.getText().trim(),
                        this.streetField.getText().trim(),
                        this.numberField.getText().trim(),
                        this.cityField.getText().trim(),
                        this.stateField.getSelectionModel().getSelectedItem()
                )
        );

        this.root.getScene().getWindow().hide();
    }
}
