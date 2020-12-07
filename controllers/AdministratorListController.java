package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.App;
import shop.entities.Administrator;

public class AdministratorListController {
    @FXML
    private TableView<Administrator> table;

    @FXML
    private TableColumn<Administrator, String> nameCol;

    @FXML
    private TableColumn<Administrator, String> emailCol;

    @FXML
    private Button editBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Administradores");

        try {
            stage.setScene(new Scene(FXMLLoader.load(AdministratorListController.class.getResource("../views/AdministratorList.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os administradores. Contate o suporte.");

            alert.show();

            return;
        }

        stage.show();
    }

    public void initialize() {
        /*
         * Prepare table
         */
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        this.emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        this.table.setPlaceholder(new Label("Não há administradores para exibir"));
        this.table.setItems(App.getInstance().getAdministrators());

        /*
         * Bind properties
         */
        this.editBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openAdministratorNew() {
        AdministratorNewController.open();
    }

    public void openAdministratorEdit() {
        AdministratorEditController.open(this.table.getSelectionModel().getSelectedItem());
    }
}