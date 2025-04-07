package CQRSArchitecture.command.command;

import CQRSArchitecture.command.model.OrderStatus;

public class UpdateCookingStatusCommand {
    private final String orderId;
    private final OrderStatus newStatus;

    public UpdateCookingStatusCommand(String orderId, OrderStatus newStatus) {
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
