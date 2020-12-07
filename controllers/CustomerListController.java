package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.entities.Customer;

public class CustomerListController {
    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> idCol;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> emailCol;

    @FXML
    private Button editBtn;

    @FXML
    private Button openCustomerAddressListBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Clientes");

        try {
            stage.setScene(new Scene(FXMLLoader.load(CustomerListController.class.getResource("../views/CustomerList.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os clientes. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Prepare table
         */
        this.idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        this.phoneCol.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );
        this.emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        this.table.setPlaceholder(new Label("Não há clientes para exibir"));
        this.table.setItems(App.getInstance().getCustomers());

        /*
         * Bind properties
         */
        this.editBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
        this.openCustomerAddressListBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openCustomerNew() {
        CustomerNewController.open();
    }

    public void openCustomerEdit() {
        CustomerEditController.open("Editar cliente", "Salvar cliente", this.table.getSelectionModel().getSelectedItem());
    }

    public void openCustomerAddressList() {
        CustomerAddressListController.open(this.table.getSelectionModel().getSelectedItem().getAddresses());
    }
}