package shop.entities;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.math.BigDecimal;

public class CartItem {
    private final ReadOnlyObjectWrapper<Product> product;
    private final IntegerProperty quantity;
    private final ReadOnlyObjectWrapper<BigDecimal> total;

    public CartItem(Product product) {
        this.product = new ReadOnlyObjectWrapper<>(product);
        this.quantity = new SimpleIntegerProperty(1);
        this.total = new ReadOnlyObjectWrapper<>();

        this.total.bind(
                Bindings.createObjectBinding(
                        () -> this.product.get().getPrice().multiply(new BigDecimal(this.quantity.get())),
                        this.quantity
                )
        );
    }

    public ReadOnlyObjectProperty<Product> productProperty() {
        return this.product.getReadOnlyProperty();
    }

    public IntegerProperty quantityProperty() {
        return this.quantity;
    }

    public ReadOnlyObjectProperty<BigDecimal> totalProperty() {
        return this.total.getReadOnlyProperty();
    }

    public Product getProduct() {
        return this.product.get();
    }

    public int getQuantity() {
        return this.quantity.get();
    }

    public BigDecimal getTotal() {
        return this.total.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}
