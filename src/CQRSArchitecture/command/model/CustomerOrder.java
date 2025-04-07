package CQRSArchitecture.command.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerOrder {
    private final String orderId;
    private List<String> dishes;
    private OrderStatus status;

    public CustomerOrder() {
        this.orderId = UUID.randomUUID().toString();
        this.dishes = new ArrayList<>();
        this.status = OrderStatus.CREATED;
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

    public void addDish(String dish) {
        dishes.add(dish);
    }

    public void changeOrder(List<String> newDishes) {
        this.dishes = new ArrayList<>(newDishes);
    }

    public void updateCookingStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public void completeOrder() {
        this.status = OrderStatus.COMPLETED;
    }
}
