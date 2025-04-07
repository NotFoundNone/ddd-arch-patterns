package CQRSArchitecture.command.command;

public class AddDishCommand {
    private final String orderId;
    private final String dish;

    public AddDishCommand(String orderId, String dish) {
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
