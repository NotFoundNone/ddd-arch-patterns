package HexagonalArchitecture.adapters.secondary;

import HexagonalArchitecture.domain.port.SupplierNotificationService;

public class DummySupplierNotificationService implements SupplierNotificationService {
    @Override
    public void notifySupplier(String message) {
        System.out.println("Notification to supplier: " + message);
    }
}
