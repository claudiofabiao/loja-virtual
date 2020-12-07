package shop.entities;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class Cart {
    private final SimpleListProperty<CartItem> items;
    private final ReadOnlyObjectWrapper<BigDecimal> totalPrice;

    public Cart() {
        this.items = new SimpleListProperty<>(
                FXCollections.observableArrayList(
                        item -> new Observable[] {
                                item.totalProperty()
                        }
                )
        );
        this.totalPrice = new ReadOnlyObjectWrapper<>();
        this.totalPrice.bind(
                Bindings.createObjectBinding(
                        () -> {
                            BigDecimal totalPrice = BigDecimal.ZERO;

                            for (CartItem item: this.items) {
                                totalPrice = totalPrice.add(item.getTotal());
                            }

                            return totalPrice;
                        }, this.items
                )
        );
    }

    public ListProperty<CartItem> itemsProperty() {
        return this.items;
    }

    public ReadOnlyObjectProperty<BigDecimal> totalProperty() {
        return this.totalPrice.getReadOnlyProperty();
    }

    public ObservableList<CartItem> getItems() {
        return this.items.get();
    }

    public BigDecimal getTotal() {
        return this.totalPrice.get();
    }

    public boolean has(Product product) {
        for (CartItem item: this.items) {
            if (item.getProduct() == product) {
                return true;
            }
        }

        return false;
    }
}