package HexagonalArchitecture;

import HexagonalArchitecture.adapters.primary.ConsoleSupplyOrderAdapter;
import HexagonalArchitecture.adapters.secondary.InMemorySupplyOrderRepository;
import HexagonalArchitecture.domain.port.primary.SupplyOrderUseCase;
import HexagonalArchitecture.adapters.secondary.DummySupplierNotificationService;
import HexagonalArchitecture.domain.service.SupplyOrderServiceImpl;

public class Main {
    public static void main(String[] args) {
        InMemorySupplyOrderRepository repository = new InMemorySupplyOrderRepository();

        DummySupplierNotificationService notificationService = new DummySupplierNotificationService();

        SupplyOrderUseCase supplyOrderService = new SupplyOrderServiceImpl(repository, notificationService);

        ConsoleSupplyOrderAdapter consoleAdapter = new ConsoleSupplyOrderAdapter(supplyOrderService);

        consoleAdapter.start();
    }
}
