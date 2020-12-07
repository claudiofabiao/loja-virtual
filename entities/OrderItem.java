package shop.entities;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class OrderItem {
    private final ReadOnlyStringWrapper id;
    private final ReadOnlyStringWrapper name;
    private final ReadOnlyIntegerWrapper quantity;
    private final ReadOnlyObjectWrapper<BigDecimal> price;
    private final ReadOnlyObjectWrapper<BigDecimal> total;

    public OrderItem(Product product, int quantity) {
        this.id = new ReadOnlyStringWrapper(product.getId());
        this.name = new ReadOnlyStringWrapper(product.getName());
        this.quantity = new ReadOnlyIntegerWrapper(quantity);
        this.price = new ReadOnlyObjectWrapper<>(product.getPrice());
        this.total = new ReadOnlyObjectWrapper<>(product.getPrice().multiply(new BigDecimal(quantity)));
    }

    public ReadOnlyStringProperty idProperty() {
        return this.id.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty nameProperty() {
        return this.name.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty quantityProperty() {
        return this.quantity.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<BigDecimal> priceProperty() {
        return this.price.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<BigDecimal> totalProperty() {
        return this.total.getReadOnlyProperty();
    }

    public String getId() {
        return this.id.get();
    }

    public String getName() {
        return this.name.get();
    }

    public int getQuantity() {
        return this.quantity.get();
    }

    public BigDecimal getPrice() {
        return this.price.get();
    }

    public BigDecimal getTotal() {
        return this.total.get();
    }
}
