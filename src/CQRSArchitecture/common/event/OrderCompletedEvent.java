package CQRSArchitecture.common.event;

public class OrderCompletedEvent {
    private final String orderId;

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
