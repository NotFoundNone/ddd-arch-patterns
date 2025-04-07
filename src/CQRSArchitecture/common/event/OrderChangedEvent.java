package CQRSArchitecture.common.event;

import java.util.List;

public class OrderChangedEvent {
    private final String orderId;
    private final List<String> newDishes;

    public OrderChangedEvent(String orderId, List<String> newDishes) {
        this.orderId = orderId;
        this.newDishes = newDishes;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getNewDishes() {
        return newDishes;
    }
}
