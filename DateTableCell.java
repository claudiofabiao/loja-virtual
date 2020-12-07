package shop;

import javafx.scene.control.TableCell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTableCell<S> extends TableCell<S, LocalDate> {
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            this.setText(null);
        } else {
            this.setText(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(item));
        }
    }
}