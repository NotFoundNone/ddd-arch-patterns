package CQRSArchitecture.api.console;

import CQRSArchitecture.command.command.*;
import CQRSArchitecture.command.handler.CustomerOrderCommandHandler;
import CQRSArchitecture.command.model.OrderStatus;
import CQRSArchitecture.query.dto.CustomerOrderView;
import CQRSArchitecture.query.service.CustomerOrderQueryService;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUI {
    private final CustomerOrderCommandHandler commandHandler;
    private final CustomerOrderQueryService queryService;
    private final Scanner scanner;

    public ConsoleUI(CustomerOrderCommandHandler commandHandler, CustomerOrderQueryService queryService) {
        this.commandHandler = commandHandler;
        this.queryService = queryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = readInt();
            handleChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n=== Управление заказами (CQRS) ===");
        System.out.println("1. Создать заказ");
        System.out.println("2. Добавить блюдо в заказ");
        System.out.println("3. Изменить состав заказа");
        System.out.println("4. Обновить статус приготовления");
        System.out.println("5. Завершить заказ");
        System.out.println("6. Посмотреть состояние заказа (read-модель)");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createOrder();
                break;
            case 2:
                addDish();
                break;
            case 3:
                changeOrder();
                break;
            case 4:
                updateCookingStatus();
                break;
            case 5:
                completeOrder();
                break;
            case 6:
                getOrderView();
                break;
            case 0:
                System.out.println("Выход...");
                break;
            default:
                System.out.println("Неверная опция!");
        }
    }

    private void createOrder() {
        String orderId = commandHandler.handle(new CreateOrderCommand());
        System.out.println("Создан заказ с ID: " + orderId);
    }

    private void addDish() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        System.out.print("Введите название блюда: ");
        String dish = scanner.nextLine();
        commandHandler.handle(new AddDishCommand(orderId, dish));
        System.out.println("Блюдо добавлено.");
    }

    private void changeOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        System.out.print("Введите новые блюда (через запятую): ");
        String dishesStr = scanner.nextLine();
        String[] dishesArr = dishesStr.split(",");
        commandHandler.handle(new ChangeOrderCommand(orderId, Arrays.asList(dishesArr)));
        System.out.println("Состав заказа изменён.");
    }

    private void updateCookingStatus() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        System.out.println("Выберите статус (1-COOKING, 2-READY, 3-COMPLETED): ");
        int statusChoice = readInt();
        OrderStatus status;
        switch (statusChoice) {
            case 1: status = OrderStatus.COOKING; break;
            case 2: status = OrderStatus.READY; break;
            case 3: status = OrderStatus.COMPLETED; break;
            default:
                System.out.println("Неверный выбор статуса!");
                return;
        }
        commandHandler.handle(new UpdateCookingStatusCommand(orderId, status));
        System.out.println("Статус обновлён.");
    }

    private void completeOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        commandHandler.handle(new CompleteOrderCommand(orderId));
        System.out.println("Заказ завершён.");
    }

    private void getOrderView() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        CustomerOrderView view = queryService.getOrderView(orderId);
        if (view == null) {
            System.out.println("Заказ не найден в read-модели.");
        } else {
            System.out.println("Состояние заказа: " + view);
        }
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
}
