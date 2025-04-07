package HexagonalArchitecture.adapters.primary;

import HexagonalArchitecture.domain.model.Forecast;
import HexagonalArchitecture.domain.model.SupplyOrder;
import HexagonalArchitecture.domain.port.SupplyOrderUseCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleSupplyOrderAdapter {
    private final SupplyOrderUseCase supplyOrderUseCase;
    private final Scanner scanner;
    
    public ConsoleSupplyOrderAdapter(SupplyOrderUseCase supplyOrderUseCase) {
        this.supplyOrderUseCase = supplyOrderUseCase;
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
        System.out.println("\n===== Подсистема обработки заказов поставщикам =====");
        System.out.println("1. Создать заказ поставщику на основе прогноза");
        System.out.println("2. Отправить заказ поставщику");
        System.out.println("3. Подтвердить заказ");
        System.out.println("4. Отметить приемку поставки");
        System.out.println("5. Обработать возврат");
        System.out.println("6. Получить информацию о заказе");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }
    
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число.");
            scanner.next();
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return num;
    }
    
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                createOrder();
                break;
            case 2:
                sendOrder();
                break;
            case 3:
                confirmOrder();
                break;
            case 4:
                markDeliveryReceived();
                break;
            case 5:
                processReturn();
                break;
            case 6:
                getOrderInfo();
                break;
            case 0:
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
    
    private void createOrder() {
        System.out.println("Введите данные прогноза.");
        // Для простоты спрашиваем только количество одного продукта.
        System.out.print("Введите требуемое количество продукта (Product1): ");
        int quantity = readInt();
        Map<String, Integer> productDemand = new HashMap<>();
        productDemand.put("Product1", quantity);
        Forecast forecast = new Forecast(productDemand);
        SupplyOrder order = supplyOrderUseCase.createOrderFromForecast(forecast);
        System.out.println("Заказ создан. ID заказа: " + order.getId());
    }
    
    private void sendOrder() {
        System.out.print("Введите ID заказа для отправки: ");
        String orderId = scanner.nextLine();
        supplyOrderUseCase.sendOrder(orderId);
    }
    
    private void confirmOrder() {
        System.out.print("Введите ID заказа для подтверждения: ");
        String orderId = scanner.nextLine();
        supplyOrderUseCase.confirmOrder(orderId);
    }
    
    private void markDeliveryReceived() {
        System.out.print("Введите ID заказа для отметки приемки: ");
        String orderId = scanner.nextLine();
        supplyOrderUseCase.markDeliveryReceived(orderId);
    }
    
    private void processReturn() {
        System.out.print("Введите ID заказа для обработки возврата: ");
        String orderId = scanner.nextLine();
        supplyOrderUseCase.processReturn(orderId);
    }
    
    private void getOrderInfo() {
        System.out.print("Введите ID заказа для получения информации: ");
        String orderId = scanner.nextLine();
        SupplyOrder order = supplyOrderUseCase.getOrder(orderId);
        if (order != null) {
            System.out.println("Информация о заказе: " + order);
        } else {
            System.out.println("Заказ не найден.");
        }
    }
}
