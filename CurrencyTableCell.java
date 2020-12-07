package shop;

import javafx.scene.control.TableCell;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class CurrencyTableCell<S> extends TableCell<S, BigDecimal> {
    @Override
    public void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            this.setText(null);
        } else {
            this.setText(NumberFormat.getCurrencyInstance().format(item));
        }
    }
}