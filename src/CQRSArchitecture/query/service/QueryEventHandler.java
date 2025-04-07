package CQRSArchitecture.query.service;

import CQRSArchitecture.command.model.OrderStatus;
import CQRSArchitecture.common.event.*;
import CQRSArchitecture.query.dto.CustomerOrderView;
import CQRSArchitecture.query.repository.CustomerOrderViewRepository;

import java.util.ArrayList;
import java.util.List;

public class QueryEventHandler {
    private final CustomerOrderViewRepository viewRepository;

    public QueryEventHandler(CustomerOrderViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public void handle(OrderCreatedEvent event) {
        // При создании заказа в read-модель записываем пустой список блюд и статус CREATED
        CustomerOrderView view = new CustomerOrderView(event.getOrderId(), new ArrayList<>(), OrderStatus.CREATED);
        viewRepository.save(view);
    }

    public void handle(DishAddedEvent event) {
        CustomerOrderView existing = viewRepository.findById(event.getOrderId());
        if (existing != null) {
            List<String> updatedDishes = new ArrayList<>(existing.getDishes());
            updatedDishes.add(event.getDish());
            CustomerOrderView newView = new CustomerOrderView(
                    existing.getOrderId(),
                    updatedDishes,
                    existing.getStatus()
            );
            viewRepository.save(newView);
        }
    }

    public void handle(OrderChangedEvent event) {
        CustomerOrderView existing = viewRepository.findById(event.getOrderId());
        if (existing != null) {
            CustomerOrderView newView = new CustomerOrderView(
                    existing.getOrderId(),
                    event.getNewDishes(),
                    existing.getStatus()
            );
            viewRepository.save(newView);
        }
    }

    public void handle(CookingStatusUpdatedEvent event) {
        CustomerOrderView existing = viewRepository.findById(event.getOrderId());
        if (existing != null) {
            CustomerOrderView newView = new CustomerOrderView(
                    existing.getOrderId(),
                    existing.getDishes(),
                    event.getNewStatus()
            );
            viewRepository.save(newView);
        }
    }

    public void handle(OrderCompletedEvent event) {
        CustomerOrderView existing = viewRepository.findById(event.getOrderId());
        if (existing != null) {
            CustomerOrderView newView = new CustomerOrderView(
                    existing.getOrderId(),
                    existing.getDishes(),
                    OrderStatus.COMPLETED
            );
            viewRepository.save(newView);
        }
    }
}
