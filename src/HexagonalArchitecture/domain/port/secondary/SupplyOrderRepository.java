package HexagonalArchitecture.domain.port.secondary;

import HexagonalArchitecture.domain.model.SupplyOrder;

public interface SupplyOrderRepository {
    void save(SupplyOrder order);
    SupplyOrder findById(String orderId);
}
