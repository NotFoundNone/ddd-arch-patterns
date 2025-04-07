package CQRSArchitecture.query.service;

import CQRSArchitecture.query.dto.CustomerOrderView;
import CQRSArchitecture.query.repository.CustomerOrderViewRepository;

public class CustomerOrderQueryService {
    private final CustomerOrderViewRepository viewRepository;

    public CustomerOrderQueryService(CustomerOrderViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public CustomerOrderView getOrderView(String orderId) {
        return viewRepository.findById(orderId);
    }
}
