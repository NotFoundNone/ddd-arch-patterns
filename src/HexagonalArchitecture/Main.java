package HexagonalArchitecture;

import HexagonalArchitecture.adapters.primary.ConsoleSupplyOrderAdapter;
import HexagonalArchitecture.adapters.secondary.InMemorySupplyOrderRepository;
import HexagonalArchitecture.domain.port.primary.SupplyOrderUseCase;
import HexagonalArchitecture.adapters.secondary.DummySupplierNotificationService;
import HexagonalArchitecture.domain.service.SupplyOrderServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Создаем выходные адаптеры (secondary adapters)
        InMemorySupplyOrderRepository repository = new InMemorySupplyOrderRepository();
        DummySupplierNotificationService notificationService = new DummySupplierNotificationService();
        
        // Создаем реализацию входного порта (ядро) и внедряем в него зависимости
        SupplyOrderUseCase supplyOrderService = new SupplyOrderServiceImpl(repository, notificationService);
        
        // Создаем входной адаптер (консольный интерфейс)
        ConsoleSupplyOrderAdapter consoleAdapter = new ConsoleSupplyOrderAdapter(supplyOrderService);
        
        // Запускаем взаимодействие с пользователем
        consoleAdapter.start();
    }
}
