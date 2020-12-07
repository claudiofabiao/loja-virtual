package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.*;
import shop.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderListController {
    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, LocalDate> dateCol;

    @FXML
    private TableColumn<Order, String> customerIdCol;

    @FXML
    private TableColumn<Order, String> customerNameCol;

    @FXML
    private TableColumn<Order, String> customerPhoneCol;

    @FXML
    private TableColumn<Order, String> customerEmailCol;

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

    @FXML
    private Button openOrderStatusEditBtn;

    public static void open() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pedidos");

        try {
            stage.setScene(new Scene(FXMLLoader.load(ProductListController.class.getResource("../views/OrderList.fxml"))));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os pedidos. Contate o suporte.");

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
        this.dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        this.customerIdCol.setCellValueFactory(
                cellData -> cellData.getValue().getCustomer().idProperty()
        );
        this.customerNameCol.setCellValueFactory(
                cellData -> cellData.getValue().getCustomer().fullNameProperty()
        );
        this.customerPhoneCol.setCellValueFactory(
                cellData -> cellData.getValue().getCustomer().phoneProperty()
        );
        this.customerEmailCol.setCellValueFactory(
                cellData -> cellData.getValue().getCustomer().emailProperty()
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
        this.table.setItems(App.getInstance().getOrders());

        /*
         * Bind properties
         */
        this.openOrderItemListBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
        this.openOrderStatusEditBtn.disableProperty().bind(this.table.getSelectionModel().selectedItemProperty().isNull());
    }

    public void openOrderItemList() {
        OrderItemListController.open(this.table.getSelectionModel().getSelectedItem().getItems());
    }

    public void openOrderStatusEdit() {
        OrderStatusEditController.open(this.table.getSelectionModel().getSelectedItem());
    }
}