package CQRSArchitecture.command.handler;

import CQRSArchitecture.command.command.*;
import CQRSArchitecture.command.model.CustomerOrder;
import CQRSArchitecture.command.repository.CustomerOrderRepository;
import CQRSArchitecture.common.event.*;

import java.util.List;

public class CustomerOrderCommandHandler {
    private final CustomerOrderRepository repository;
    private final EventPublisher eventPublisher;

    public CustomerOrderCommandHandler(CustomerOrderRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public String handle(CreateOrderCommand command) {
        CustomerOrder order = new CustomerOrder();
        repository.save(order);
        eventPublisher.publish(new OrderCreatedEvent(order.getOrderId()));
        return order.getOrderId();
    }

    public void handle(AddDishCommand command) {
        CustomerOrder order = repository.findById(command.getOrderId());
        if (order != null) {
            order.addDish(command.getDish());
            repository.save(order);
            eventPublisher.publish(new DishAddedEvent(order.getOrderId(), command.getDish()));
        }
    }

    public void handle(ChangeOrderCommand command) {
        CustomerOrder order = repository.findById(command.getOrderId());
        if (order != null) {
            order.changeOrder(command.getNewDishes());
            repository.save(order);
            eventPublisher.publish(new OrderChangedEvent(order.getOrderId(), command.getNewDishes()));
        }
    }

    public void handle(UpdateCookingStatusCommand command) {
        CustomerOrder order = repository.findById(command.getOrderId());
        if (order != null) {
            order.updateCookingStatus(command.getNewStatus());
            repository.save(order);
            eventPublisher.publish(new CookingStatusUpdatedEvent(order.getOrderId(), command.getNewStatus()));
        }
    }

    public void handle(CompleteOrderCommand command) {
        CustomerOrder order = repository.findById(command.getOrderId());
        if (order != null) {
            order.completeOrder();
            repository.save(order);
            eventPublisher.publish(new OrderCompletedEvent(order.getOrderId()));
        }
    }
}
