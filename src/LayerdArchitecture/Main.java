package LayerdArchitecture;

import LayerdArchitecture.application.InventoryService;
import LayerdArchitecture.infrastructure.InMemoryInventoryRepository;
import LayerdArchitecture.presentation.InventoryConsoleUI;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр репозитория (инфраструктурный слой)
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        // Создаем сервис для управления инвентаризацией (слой приложения)
        InventoryService inventoryService = new InventoryService(repository);

        // При необходимости можно добавить тестовые данные
        // inventoryService.addProduct("P001", "Мясо", LocalDate.parse("2025-06-01"), 10, 50);
        // inventoryService.addProduct("P002", "Овощи", LocalDate.parse("2025-05-15"), 20, 30);

        // Создаем консольный интерфейс (слой представления)
        InventoryConsoleUI ui = new InventoryConsoleUI(inventoryService);
        // Запускаем приложение
        ui.start();
    }
}