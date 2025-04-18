package HexagonalArchitecture.adapters.primary;

import HexagonalArchitecture.domain.model.Product;
import HexagonalArchitecture.domain.model.SupplyItem;
import HexagonalArchitecture.domain.model.SupplyOrder;
import HexagonalArchitecture.domain.port.primary.SupplyOrderUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleSupplyOrderAdapter {
    private final SupplyOrderUseCase useCase;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleSupplyOrderAdapter(SupplyOrderUseCase useCase) {
        this.useCase = useCase;
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
        System.out.println("\n=== Подсистема заказов поставщикам ===");
        System.out.println("1. Создать заказ");
        System.out.println("2. Отправить заказ");
        System.out.println("3. Подтвердить заказ");
        System.out.println("4. Отметить доставку");
        System.out.println("5. Обработать возврат");
        System.out.println("6. Показать заказ");
        System.out.println("0. Выход");
        System.out.print("Выбор: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1: createOrder(); break;
            case 2: sendOrder(); break;
            case 3: confirmOrder(); break;
            case 4: markDelivery(); break;
            case 5: processReturn(); break;
            case 6: showOrder(); break;
            case 0: System.out.println("Выход..."); break;
            default: System.out.println("Неверный выбор.");
        }
    }

    private void createOrder() {
        List<SupplyItem> items = new ArrayList<>();
        while (true) {
            System.out.print("Введите ID продукта (или пусто для завершения): ");
            String pid = scanner.nextLine().trim();
            if (pid.isEmpty()) break;
            System.out.print("Введите название продукта: ");
            String name = scanner.nextLine().trim();
            System.out.print("Введите количество: ");
            int qty = readInt();
            items.add(new SupplyItem(new Product(pid, name), qty));
        }
        if (items.isEmpty()) {
            System.out.println("Ни одного элемента — заказ не создан.");
            return;
        }
        SupplyOrder order = useCase.createOrder(items);
        System.out.println("Создан заказ: " + order.getId());
    }

    private void sendOrder() {
        System.out.print("ID заказа для отправки: ");
        String id = scanner.nextLine().trim();
        useCase.sendOrder(id);
    }

    private void confirmOrder() {
        System.out.print("ID заказа для подтверждения: ");
        String id = scanner.nextLine().trim();
        useCase.confirmOrder(id);
    }

    private void markDelivery() {
        System.out.print("ID заказа для отметки доставки: ");
        String id = scanner.nextLine().trim();
        useCase.markDeliveryReceived(id);
    }

    private void processReturn() {
        System.out.print("ID заказа для возврата: ");
        String id = scanner.nextLine().trim();
        useCase.processReturn(id);
    }

    private void showOrder() {
        System.out.print("ID заказа для показа: ");
        String id = scanner.nextLine().trim();
        SupplyOrder o = useCase.getOrder(id);
        if (o == null) {
            System.out.println("Заказ не найден.");
        } else {
            System.out.println(o);
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }
}
