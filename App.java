package shop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import shop.controllers.CustomerSignInController;
import shop.entities.Administrator;
import shop.entities.Customer;
import shop.entities.Order;
import shop.entities.Product;

public class App extends Application {
    private static App instance;

    private final ObservableList<Administrator> administrators = FXCollections.observableArrayList();
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<Order> orders = FXCollections.observableArrayList();

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public ObservableList<Administrator> getAdministrators() {
        return this.administrators;
    }

    public ObservableList<Customer> getCustomers() {
        return this.customers;
    }

    public ObservableList<Product> getProducts() {
        return this.products;
    }

    public ObservableList<Order> getOrders() {
        return this.orders;
    }

    public void start(Stage stage) {
        CustomerSignInController.open();

        customers.add(new Customer("18664585741", "Claudio Junior", "Fabião", "21983993018", "a", "a"));
        administrators.add(new Administrator("Claudio Junior", "Fabião", "a", "a"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}