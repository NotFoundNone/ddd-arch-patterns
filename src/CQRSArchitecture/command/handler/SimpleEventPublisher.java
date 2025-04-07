package CQRSArchitecture.command.handler;

import CQRSArchitecture.common.event.*;
import CQRSArchitecture.query.service.QueryEventHandler;

public class SimpleEventPublisher implements EventPublisher {
    private final QueryEventHandler queryEventHandler;

    public SimpleEventPublisher(QueryEventHandler queryEventHandler) {
        this.queryEventHandler = queryEventHandler;
    }

    @Override
    public void publish(Object event) {
        if (event instanceof OrderCreatedEvent) {
            queryEventHandler.handle((OrderCreatedEvent) event);
        } else if (event instanceof DishAddedEvent) {
            queryEventHandler.handle((DishAddedEvent) event);
        } else if (event instanceof OrderChangedEvent) {
            queryEventHandler.handle((OrderChangedEvent) event);
        } else if (event instanceof CookingStatusUpdatedEvent) {
            queryEventHandler.handle((CookingStatusUpdatedEvent) event);
        } else if (event instanceof OrderCompletedEvent) {
            queryEventHandler.handle((OrderCompletedEvent) event);
        }
    }
}
