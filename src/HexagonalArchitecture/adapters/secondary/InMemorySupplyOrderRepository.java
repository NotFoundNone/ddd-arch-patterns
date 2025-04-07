package HexagonalArchitecture.adapters.secondary;

import HexagonalArchitecture.domain.model.SupplyOrder;
import HexagonalArchitecture.domain.port.SupplyOrderRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemorySupplyOrderRepository implements SupplyOrderRepository {
    private final Map<String, SupplyOrder> orderMap = new HashMap<>();
    
    @Override
    public void save(SupplyOrder order) {
        orderMap.put(order.getId(), order);
    }
    
    @Override
    public SupplyOrder findById(String orderId) {
        return orderMap.get(orderId);
    }
}
