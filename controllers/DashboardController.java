package shop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.entities.Administrator;

public class DashboardController {
    @FXML
    private Parent root;

    @FXML
    private Text welcomeText;

    @FXML
    private Text emailText;

    public static void open(Administrator administrator) {
        FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource("../views/Dashboard.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Painel administrativo");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar entrar. Contate o suporte.");

            alert.show();

            return;
        }

        ((DashboardController)loader.getController()).setData(administrator);

        stage.show();
    }

    public void setData(Administrator administrator) {
        this.welcomeText.textProperty().bind(Bindings.concat("Olá, ", administrator.fullNameProperty()));
        this.emailText.textProperty().bind(administrator.emailProperty());
    }

    public void openProductList() {
        ProductListController.open();
    }

    public void openCustomerList() {
        CustomerListController.open();
    }

    public void openOrderList() {
        OrderListController.open();
    }

    public void openAdministratorList() {
        AdministratorListController.open();
    }

    public void signOut() {
        this.root.getScene().getWindow().hide();
        AdminSignInController.open();
    }
}