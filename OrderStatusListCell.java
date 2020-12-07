package shop;

import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import shop.entities.Order;

public class OrderStatusListCell extends ListCell<Integer> {
    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            this.setText(null);
        } else {
            switch (item) {
                case Order.CANCELED_STATUS -> this.setText("Cancelado");
                case Order.AWAITING_PAYMENT_CONFIRMATION_STATUS -> this.setText("Aguardando confirmação do pagamento");
                case Order.SEPARATING_STATUS -> this.setText("Em separação");
                case Order.SENT_STATUS -> this.setText("Enviado ao transportador");
                case Order.DELIVERED_STATUS -> this.setText("Entregue");
            }
        }
    }
}