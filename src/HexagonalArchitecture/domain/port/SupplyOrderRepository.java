package HexagonalArchitecture.domain.port;

import HexagonalArchitecture.domain.model.SupplyOrder;

public interface SupplyOrderRepository {
    void save(SupplyOrder order);
    SupplyOrder findById(String orderId);
}
