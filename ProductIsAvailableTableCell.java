package shop;

import javafx.scene.control.TableCell;

public class ProductIsAvailableTableCell<S> extends TableCell<S, Boolean> {
    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            this.setText(null);
        } else {
            this.setText(item ? "Disponível": "Indisponível");
        }
    }
}