package CQRSArchitecture.query.repository;

import CQRSArchitecture.query.dto.CustomerOrderView;

public interface CustomerOrderViewRepository {
    void save(CustomerOrderView view);
    CustomerOrderView findById(String orderId);
}
