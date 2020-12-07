package shop.entities;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class CreditCard {
    private final ReadOnlyStringWrapper holderName;
    private final ReadOnlyStringWrapper number;
    private final ReadOnlyStringWrapper expirationDate;
    private final ReadOnlyStringWrapper securityCode;

    public CreditCard(String holderName, String number, String expirationDate, String securityCode) {
        this.holderName = new ReadOnlyStringWrapper(holderName);
        this.number = new ReadOnlyStringWrapper(number);
        this.expirationDate = new ReadOnlyStringWrapper(expirationDate);
        this.securityCode = new ReadOnlyStringWrapper(securityCode);
    }

    public ReadOnlyStringProperty holderNameProperty() {
        return this.holderName.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty numberProperty() {
        return this.number.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty expirationDateProperty() {
        return this.expirationDate.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty securityCodeProperty() {
        return this.securityCode.getReadOnlyProperty();
    }

    public String getHolderName() {
        return this.holderName.get();
    }

    public String getNumber() {
        return this.number.get();
    }

    public String getExpirationDate() {
        return this.expirationDate.get();
    }

    public String getSecurityCode() {
        return this.securityCode.get();
    }
}
