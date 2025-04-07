package HexagonalArchitecture.domain.service;

import HexagonalArchitecture.domain.model.Forecast;
import HexagonalArchitecture.domain.model.OrderStatus;
import HexagonalArchitecture.domain.model.SupplyOrder;
import HexagonalArchitecture.domain.port.SupplierNotificationService;
import HexagonalArchitecture.domain.port.SupplyOrderRepository;
import HexagonalArchitecture.domain.port.SupplyOrderUseCase;

public class SupplyOrderServiceImpl implements SupplyOrderUseCase {
    private final SupplyOrderRepository repository;
    private final SupplierNotificationService notificationService;
    
    public SupplyOrderServiceImpl(SupplyOrderRepository repository, SupplierNotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }
    
    @Override
    public SupplyOrder createOrderFromForecast(Forecast forecast) {
        // В реальном приложении данные прогноза будут использоваться для формирования деталей заказа.
        SupplyOrder order = new SupplyOrder();
        repository.save(order);
        System.out.println("Order created: " + order);
        return order;
    }
    
    @Override
    public void sendOrder(String orderId) {
        SupplyOrder order = repository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.SENT);
            repository.save(order);
            notificationService.notifySupplier("Order " + orderId + " has been sent.");
            System.out.println("Order sent: " + order);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }
    
    @Override
    public void confirmOrder(String orderId) {
        SupplyOrder order = repository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.CONFIRMED);
            repository.save(order);
            System.out.println("Order confirmed: " + order);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }
    
    @Override
    public void markDeliveryReceived(String orderId) {
        SupplyOrder order = repository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.DELIVERED);
            repository.save(order);
            System.out.println("Delivery received for order: " + order);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }
    
    @Override
    public void processReturn(String orderId) {
        SupplyOrder order = repository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.RETURNED);
            repository.save(order);
            notificationService.notifySupplier("Return processed for order " + orderId);
            System.out.println("Return processed for order: " + order);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }
    
    @Override
    public SupplyOrder getOrder(String orderId) {
        return repository.findById(orderId);
    }
}
