package HexagonalArchitecture.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SupplyOrder {
    private final String id;
    private final LocalDateTime createdAt;
    private List<SupplyItem> items;
    private OrderStatus status;

    public SupplyOrder(List<SupplyItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>(items);
        this.status = OrderStatus.CREATED;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<SupplyItem> getItems() {
        List<SupplyItem> supplyItems = new ArrayList<>();
        supplyItems.addAll(items);
        return supplyItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SupplyOrder{" +
               "id='" + id + '\'' +
               ", createdAt=" + createdAt +
               ", items=" + items +
               ", status=" + status +
               '}';
    }
}
