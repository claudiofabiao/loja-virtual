package shop.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.CurrencyTableCell;
import shop.DateTableCell;
import shop.OrderStatusTableCell;
import shop.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerOrderListController {
    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, LocalDate> dateCol;

    @FXML
    private TableColumn<Order, String> addressStreetCol;

    @FXML
    private TableColumn<Order, String> addressNumberCol;

    @FXML
    private TableColumn<Order, String> addressCityCol;

    @FXML
    private TableColumn<Order, String> addressStateCol;

    @FXML
    private TableColumn<Order, String> addressZipCodeCol;

    @FXML
    private TableColumn<Order, BigDecimal> totalCol;

    @FXML
    private TableColumn<Order, Integer> statusCol;

    @FXML
    private Button openOrderItemListBtn;

    public static void open(ObservableList<Order> orders) {
        FXMLLoader loader = new FXMLLoader(OrderItemListController.class.getResource("../views/CustomerOrderList.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Meus pedidos");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os seus pedidos. Contate o suporte.");

            alert.show();

            return;
        }

        ((CustomerOrderListController)loader.getController()).setData(orders);

        stage.show();
    }

    public void setData(ObservableList<Order> orders) {
        /*
         * Prepare table
         */
        this.idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        this.dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        this.addressStreetCol.setCellValueFactory(
                cellData -> cellData.getValue().getAddress().streetProperty()
        );
        this.addressNumberCol.setCellValueFactory(
                cellData -> cellData.getValue().getAddress().numberProperty()
        );
        this.addressCityCol.setCellValueFactory(
                cellData -> cellData.getValue().getAddress().cityProperty()
        );
        this.addressStateCol.setCellValueFactory(
                cellData -> cellData.getValue().getAddress().stateProperty()
        );
        this.addressZipCodeCol.setCellValueFactory(
                cellData -> cellData.getValue().getAddress().zipCodeProperty()
        );
        this.totalCol.setCellValueFactory(
                new PropertyValueFactory<>("total")
        );
        this.statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        this.dateCol.setCellFactory(
                col -> new DateTableCell<>()
        );
        this.totalCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );
        this.statusCol.setCellFactory(
                col -> new OrderStatusTableCell<>()
        );

        this.table.setPlaceholder(new Label("Não há pedidos para exibir"));
        this.table.setItems(orders);

        /*
         * Bind properties
         */
        this.openOrderItemListBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openOrderItemList() {
        OrderItemListController.open(this.table.getSelectionModel().getSelectedItem().getItems());
    }
}