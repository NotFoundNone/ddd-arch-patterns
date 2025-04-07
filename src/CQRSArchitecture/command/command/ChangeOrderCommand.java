package CQRSArchitecture.command.command;

import java.util.List;

public class ChangeOrderCommand {
    private final String orderId;
    private final List<String> newDishes;

    public ChangeOrderCommand(String orderId, List<String> newDishes) {
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
