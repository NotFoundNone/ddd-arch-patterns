package CQRSArchitecture.common.event;

public class DishAddedEvent {
    private final String orderId;
    private final String dish;

    public DishAddedEvent(String orderId, String dish) {
        this.orderId = orderId;
        this.dish = dish;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDish() {
        return dish;
    }
}
