package CQRSArchitecture.command.repository;

import CQRSArchitecture.command.model.CustomerOrder;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCustomerOrderRepository implements CustomerOrderRepository {
    private final Map<String, CustomerOrder> store = new HashMap<>();

    @Override
    public void save(CustomerOrder order) {
        store.put(order.getOrderId(), order);
    }

    @Override
    public CustomerOrder findById(String orderId) {
        return store.get(orderId);
    }
}
