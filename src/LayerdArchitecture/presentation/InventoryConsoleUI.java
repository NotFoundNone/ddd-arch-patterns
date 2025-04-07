package LayerdArchitecture.presentation;

import LayerdArchitecture.application.InventoryService;
import LayerdArchitecture.domain.InventoryItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InventoryConsoleUI {
    private final InventoryService inventoryService;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public InventoryConsoleUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = readInt();
            handleMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Управление инвентаризацией =====");
        System.out.println("1. Добавить новый продукт");
        System.out.println("2. Списать продукт при использовании");
        System.out.println("3. Списать просроченные продукты");
        System.out.println("4. Провести инвентаризацию и корректировку запасов");
        System.out.println("5. Сгенерировать отчет о текущих запасах");
        System.out.println("6. Показать продукты с критическим уровнем запасов");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return result;
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addNewProduct();
                break;
            case 2:
                useProduct();
                break;
            case 3:
                writeOffExpired();
                break;
            case 4:
                adjustInventory();
                break;
            case 5:
                generateReport();
                break;
            case 6:
                showCriticalProducts();
                break;
            case 0:
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверная опция. Попробуйте снова.");
        }
    }

    private void addNewProduct() {
        System.out.print("Введите ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату истечения (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate expiryDate = LocalDate.parse(dateStr, formatter);
        System.out.print("Введите критический уровень запасов: ");
        int criticalLevel = readInt();
        System.out.print("Введите количество продукта: ");
        int quantity = readInt();
        inventoryService.addProduct(id, name, expiryDate, criticalLevel, quantity);
        System.out.println("Продукт добавлен успешно.");
    }

    private void useProduct() {
        System.out.print("Введите ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Введите количество для списания: ");
        int quantity = readInt();
        boolean success = inventoryService.useProduct(id, quantity);
        if (success) {
            System.out.println("Продукт успешно списан.");
        } else {
            System.out.println("Недостаточно запасов или продукт не найден.");
        }
    }

    private void writeOffExpired() {
        LocalDate currentDate = LocalDate.now();
        inventoryService.writeOffExpiredProducts(currentDate);
        System.out.println("Просроченные продукты списаны.");
    }

    private void adjustInventory() {
        System.out.print("Введите ID продукта для корректировки: ");
        String id = scanner.nextLine();
        System.out.print("Введите новое количество: ");
        int newQuantity = readInt();
        inventoryService.adjustInventory(id, newQuantity);
        System.out.println("Инвентаризация скорректирована.");
    }

    private void generateReport() {
        List<InventoryItem> items = inventoryService.getAllInventory();
        System.out.println("\n=== Отчет о запасах ===");
        for (InventoryItem item : items) {
            System.out.println("ID: " + item.getProduct().getId() +
                    ", Название: " + item.getProduct().getName() +
                    ", Количество: " + item.getQuantity() +
                    ", Дата истечения: " + item.getProduct().getExpiryDate() +
                    ", Критический уровень: " + item.getProduct().getCriticalLevel());
        }
    }

    private void showCriticalProducts() {
        List<InventoryItem> criticalItems = inventoryService.getCriticalProducts();
        System.out.println("\n=== Продукты с критическим уровнем запасов ===");
        if (criticalItems.isEmpty()) {
            System.out.println("Нет продуктов с критическим уровнем запасов.");
        } else {
            for (InventoryItem item : criticalItems) {
                System.out.println("ID: " + item.getProduct().getId() +
                        ", Название: " + item.getProduct().getName() +
                        ", Количество: " + item.getQuantity() +
                        ", Критический уровень: " + item.getProduct().getCriticalLevel());
            }
        }
    }
}
