package CQRSArchitecture.command.command;

public class CompleteOrderCommand {
    private final String orderId;

    public CompleteOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
