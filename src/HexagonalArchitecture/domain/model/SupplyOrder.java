package HexagonalArchitecture.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class SupplyOrder {
    private String id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    
    public SupplyOrder() {
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }
    
    public String getId() {
        return id;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    @Override
    public String toString() {
        return "SupplyOrder{" +
               "id='" + id + '\'' +
               ", status=" + status +
               ", createdAt=" + createdAt +
               '}';
    }
}
