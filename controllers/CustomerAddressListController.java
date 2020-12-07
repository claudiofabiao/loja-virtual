package shop.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Address;

public class CustomerAddressListController {
    private ObservableList<Address> addresses;

    @FXML
    private TableView<Address> table;

    @FXML
    private TableColumn<Address, String> nameCol;

    @FXML
    private TableColumn<Address, String> zipCodeCol;

    @FXML
    private TableColumn<Address, String> streetCol;

    @FXML
    private TableColumn<Address, String> numberCol;

    @FXML
    private TableColumn<Address, String> cityCol;

    @FXML
    private TableColumn<Address, String> stateCol;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    public static void open(ObservableList<Address> addresses) {
        FXMLLoader loader = new FXMLLoader(CustomerAddressListController.class.getResource("../views/CustomerAddressList.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Endereços do cliente");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os endereços do cliente. Contate o suporte.");

            alert.show();

            return;
        }

        ((CustomerAddressListController)loader.getController()).setData(addresses);

        stage.show();
    }

    public void setData(ObservableList<Address> addresses) {
        this.addresses = addresses;

        /*
         * Prepare table
         */
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("zipCode")
        );
        this.streetCol.setCellValueFactory(
                new PropertyValueFactory<>("street")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        this.cityCol.setCellValueFactory(
                new PropertyValueFactory<>("city")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("state")
        );

        this.table.setPlaceholder(new Label("Não há endereços para exibir"));
        this.table.setItems(addresses);

        /*
         * Bind properties
         */
        this.editBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
        this.removeBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openCustomerAddressNew() {
        CustomerAddressNewController.open(this.addresses);
    }

    public void openCustomerAddressEdit() {
        CustomerAddressEditController.open(this.table.getSelectionModel().getSelectedItem());
    }

    public void remove() {
        this.addresses.remove(this.table.getSelectionModel().getSelectedItem());
    }
}