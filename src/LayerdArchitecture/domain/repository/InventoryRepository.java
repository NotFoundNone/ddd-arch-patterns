package LayerdArchitecture.domain.repository;

import LayerdArchitecture.domain.InventoryItem;

import java.util.List;

public interface InventoryRepository {
    void save(InventoryItem item);
    InventoryItem findByProductId(String productId);
    List<InventoryItem> findAll();
}
