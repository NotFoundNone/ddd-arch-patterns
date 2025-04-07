package CQRSArchitecture.command.repository;

import CQRSArchitecture.command.model.CustomerOrder;

public interface CustomerOrderRepository {
    void save(CustomerOrder order);
    CustomerOrder findById(String orderId);
}
