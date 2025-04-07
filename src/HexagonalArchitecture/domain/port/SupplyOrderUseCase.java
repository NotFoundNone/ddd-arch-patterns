package HexagonalArchitecture.domain.port;

import HexagonalArchitecture.domain.model.Forecast;
import HexagonalArchitecture.domain.model.SupplyOrder;

public interface SupplyOrderUseCase {
    SupplyOrder createOrderFromForecast(Forecast forecast);
    void sendOrder(String orderId);
    void confirmOrder(String orderId);
    void markDeliveryReceived(String orderId);
    void processReturn(String orderId);
    SupplyOrder getOrder(String orderId);
}
