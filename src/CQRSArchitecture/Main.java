package CQRSArchitecture;

import CQRSArchitecture.api.console.ConsoleUI;
import CQRSArchitecture.command.handler.CustomerOrderCommandHandler;
import CQRSArchitecture.command.handler.EventPublisher;
import CQRSArchitecture.command.handler.SimpleEventPublisher;
import CQRSArchitecture.command.repository.CustomerOrderRepository;
import CQRSArchitecture.command.repository.InMemoryCustomerOrderRepository;
import CQRSArchitecture.query.repository.CustomerOrderViewRepository;
import CQRSArchitecture.query.repository.InMemoryCustomerOrderViewRepository;
import CQRSArchitecture.query.service.CustomerOrderQueryService;
import CQRSArchitecture.query.service.QueryEventHandler;

public class Main {
    public static void main(String[] args) {
        CustomerOrderViewRepository viewRepository = new InMemoryCustomerOrderViewRepository();
        QueryEventHandler queryEventHandler = new QueryEventHandler(viewRepository);
        CustomerOrderQueryService queryService = new CustomerOrderQueryService(viewRepository);

        CustomerOrderRepository orderRepository = new InMemoryCustomerOrderRepository();
        EventPublisher eventPublisher = new SimpleEventPublisher(queryEventHandler);
        CustomerOrderCommandHandler commandHandler = new CustomerOrderCommandHandler(orderRepository, eventPublisher);

        ConsoleUI consoleUI = new ConsoleUI(commandHandler, queryService);
        consoleUI.start();
    }
}
