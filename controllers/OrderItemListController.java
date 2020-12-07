package shop.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.CurrencyTableCell;
import shop.entities.OrderItem;

import java.math.BigDecimal;

public class OrderItemListController {
    @FXML
    private TableView<OrderItem> table;

    @FXML
    private TableColumn<OrderItem, Integer> idCol;

    @FXML
    private TableColumn<OrderItem, String> nameCol;

    @FXML
    private TableColumn<OrderItem, Integer> quantityCol;

    @FXML
    private TableColumn<OrderItem, BigDecimal> priceCol;

    @FXML
    private TableColumn<OrderItem, BigDecimal> totalCol;

    public static void open(ObservableList<OrderItem> items) {
        FXMLLoader loader = new FXMLLoader(OrderItemListController.class.getResource("../views/OrderItemList.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Itens do pedido");

        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Falha inesperada no aplicativo");
            alert.setHeaderText("Falha inesperada no aplicativo");
            alert.setContentText("Ocorreu uma falha inesperada no aplicativo ao tentar listar os itens do pedido. Contate o suporte.");

            alert.show();

            return;
        }

        ((OrderItemListController)loader.getController()).setData(items);

        stage.show();
    }

    public void setData(ObservableList<OrderItem> items) {
        this.idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        this.nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        this.quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        this.priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        this.totalCol.setCellValueFactory(
                new PropertyValueFactory<>("total")
        );

        this.priceCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );
        this.totalCol.setCellFactory(
                col -> new CurrencyTableCell<>()
        );

        this.table.setPlaceholder(new Label("Não há itens para exibir"));
        this.table.setItems(items);
    }
}
