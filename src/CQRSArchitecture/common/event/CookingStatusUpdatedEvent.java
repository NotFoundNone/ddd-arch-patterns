package CQRSArchitecture.common.event;

import CQRSArchitecture.command.model.OrderStatus;

public class CookingStatusUpdatedEvent {
    private final String orderId;
    private final OrderStatus newStatus;

    public CookingStatusUpdatedEvent(String orderId, OrderStatus newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }
}
