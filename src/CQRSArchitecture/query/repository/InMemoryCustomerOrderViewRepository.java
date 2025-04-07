package CQRSArchitecture.query.repository;

import CQRSArchitecture.query.dto.CustomerOrderView;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCustomerOrderViewRepository implements CustomerOrderViewRepository {
    private final Map<String, CustomerOrderView> store = new HashMap<>();

    @Override
    public void save(CustomerOrderView view) {
        store.put(view.getOrderId(), view);
    }

    @Override
    public CustomerOrderView findById(String orderId) {
        return store.get(orderId);
    }
}
