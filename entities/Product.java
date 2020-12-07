package shop.entities;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Product {
    private final ReadOnlyStringWrapper id;
    private final StringProperty name;
    private final StringProperty brand;
    private final StringProperty size;
    private final StringProperty color;
    private final ObjectProperty<BigDecimal> price;
    private final BooleanProperty isAvailable;

    public Product(String id, String name, String brand, String size, String color, String price, boolean isAvailable) {
        this.id = new ReadOnlyStringWrapper(id);
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.size = new SimpleStringProperty(size);
        this.color = new SimpleStringProperty(color);
        this.price = new SimpleObjectProperty<>(new BigDecimal(price));
        this.isAvailable = new SimpleBooleanProperty(isAvailable);
    }

    public ReadOnlyStringProperty idProperty() {
        return this.id.getReadOnlyProperty();
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty brandProperty() {
        return this.brand;
    }

    public StringProperty sizeProperty() {
        return this.size;
    }

    public StringProperty colorProperty() {
        return this.color;
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return this.price;
    }

    public BooleanProperty isAvailableProperty() {
        return this.isAvailable;
    }

    public String getId() {
        return this.id.get();
    }

    public String getName() {
        return this.name.get();
    }

    public String getBrand() {
        return this.brand.get();
    }

    public String getSize() {
        return this.size.get();
    }

    public String getColor() {
        return this.color.get();
    }

    public BigDecimal getPrice() {
        return this.price.get();
    }

    public boolean getIsAvailable() {
        return this.isAvailable.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public void setPrice(String price) {
        this.price.set(new BigDecimal(price));
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable.set(isAvailable);
    }
}