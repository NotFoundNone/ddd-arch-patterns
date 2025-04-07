package CQRSArchitecture.query.dto;

import CQRSArchitecture.command.model.OrderStatus;

import java.util.List;

public class CustomerOrderView {
    private final String orderId;
    private final List<String> dishes;
    private final OrderStatus status;

    public CustomerOrderView(String orderId, List<String> dishes, OrderStatus status) {
        this.orderId = orderId;
        this.dishes = dishes;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CustomerOrderView{" +
                "orderId='" + orderId + '\'' +
                ", dishes=" + dishes +
                ", status=" + status +
                '}';
    }
}
