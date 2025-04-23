package LayerdArchitecture;

import LayerdArchitecture.application.InventoryService;
import LayerdArchitecture.infrastructure.InMemoryInventoryRepository;
import LayerdArchitecture.presentation.InventoryConsoleUI;

public class Main {
    public static void main(String[] args) {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();

        InventoryService inventoryService = new InventoryService(repository);

        InventoryConsoleUI ui = new InventoryConsoleUI(inventoryService);

        ui.start();
    }
}