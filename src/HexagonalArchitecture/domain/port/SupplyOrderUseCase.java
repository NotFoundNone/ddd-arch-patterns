package HexagonalArchitecture.domain.port;

import HexagonalArchitecture.domain.model.SupplyItem;
import HexagonalArchitecture.domain.model.SupplyOrder;

import java.util.List;

public interface SupplyOrderUseCase {
    SupplyOrder createOrder(List<SupplyItem> items);
    void sendOrder(String orderId);
    void confirmOrder(String orderId);
    void markDeliveryReceived(String orderId);
    void processReturn(String orderId);
    SupplyOrder getOrder(String orderId);
}

