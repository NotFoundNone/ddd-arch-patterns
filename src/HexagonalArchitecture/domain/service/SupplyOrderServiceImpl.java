package HexagonalArchitecture.domain.service;

import HexagonalArchitecture.domain.model.OrderStatus;
import HexagonalArchitecture.domain.model.SupplyItem;
import HexagonalArchitecture.domain.model.SupplyOrder;
import HexagonalArchitecture.domain.port.secondary.SupplierNotificationService;
import HexagonalArchitecture.domain.port.secondary.SupplyOrderRepository;
import HexagonalArchitecture.domain.port.primary.SupplyOrderUseCase;

import java.util.List;

public class SupplyOrderServiceImpl implements SupplyOrderUseCase {
    private final SupplyOrderRepository repository;
    private final SupplierNotificationService notificationService;

    public SupplyOrderServiceImpl(SupplyOrderRepository repository,
                                  SupplierNotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public SupplyOrder createOrder(List<SupplyItem> items) {
        SupplyOrder order = new SupplyOrder(items);
        repository.save(order);
        System.out.println("Order created: " + order.getId());
        return order;
    }

    @Override
    public void sendOrder(String orderId) {
        SupplyOrder o = repository.findById(orderId);
        if (o != null) {
            o.setStatus(OrderStatus.SENT);
            repository.save(o);
            notificationService.notifySupplier("Order " + orderId + " SENT");
            System.out.println("Order sent: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    @Override
    public void confirmOrder(String orderId) {
        SupplyOrder o = repository.findById(orderId);
        if (o != null) {
            o.setStatus(OrderStatus.CONFIRMED);
            repository.save(o);
            System.out.println("Order confirmed: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    @Override
    public void markDeliveryReceived(String orderId) {
        SupplyOrder o = repository.findById(orderId);
        if (o != null) {
            o.setStatus(OrderStatus.DELIVERED);
            repository.save(o);
            System.out.println("Delivery received: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    @Override
    public void processReturn(String orderId) {
        SupplyOrder o = repository.findById(orderId);
        if (o != null) {
            o.setStatus(OrderStatus.RETURNED);
            repository.save(o);
            notificationService.notifySupplier("Order " + orderId + " RETURNED");
            System.out.println("Return processed: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    @Override
    public SupplyOrder getOrder(String orderId) {
        return repository.findById(orderId);
    }
}
