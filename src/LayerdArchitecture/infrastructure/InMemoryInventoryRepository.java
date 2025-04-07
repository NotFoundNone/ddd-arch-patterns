package LayerdArchitecture.infrastructure;

import LayerdArchitecture.domain.InventoryItem;
import LayerdArchitecture.domain.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryInventoryRepository implements InventoryRepository {
    private Map<String, InventoryItem> inventoryMap = new HashMap<>();

    @Override
    public void save(InventoryItem item) {
        inventoryMap.put(item.getProduct().getId(), item);
    }

    @Override
    public InventoryItem findByProductId(String productId) {
        return inventoryMap.get(productId);
    }

    @Override
    public List<InventoryItem> findAll() {
        return new ArrayList<>(inventoryMap.values());
    }
}
