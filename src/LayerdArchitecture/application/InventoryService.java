package LayerdArchitecture.application;

import LayerdArchitecture.domain.InventoryItem;
import LayerdArchitecture.domain.Product;
import LayerdArchitecture.domain.repository.InventoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String id, String name, LocalDate expiryDate, int criticalLevel, int quantity) {
        Product product = new Product(id, name, expiryDate, criticalLevel);
        InventoryItem item = repository.findByProductId(id);
        if (item == null) {
            item = new InventoryItem(product, quantity);
            repository.save(item);
        } else {
            item.addQuantity(quantity);
            repository.save(item);
        }
    }

    public boolean useProduct(String productId, int quantity) {
        InventoryItem item = repository.findByProductId(productId);
        if (item != null && item.reduceQuantity(quantity)) {
            repository.save(item);
            return true;
        }
        return false;
    }

    public void writeOffExpiredProducts(LocalDate currentDate) {
        List<InventoryItem> allItems = repository.findAll();
        for (InventoryItem item : allItems) {
            if (item.getProduct().getExpiryDate().isBefore(currentDate)) {
                item.setQuantity(0);
                repository.save(item);
            }
        }
    }

    public void adjustInventory(String productId, int newQuantity) {
        InventoryItem item = repository.findByProductId(productId);
        if (item != null) {
            item.setQuantity(newQuantity);
            repository.save(item);
        }
    }

    public List<InventoryItem> getAllInventory() {
        return repository.findAll();
    }

    public List<InventoryItem> getCriticalProducts() {
        List<InventoryItem> criticalItems = new ArrayList<>();
        List<InventoryItem> allItems = repository.findAll();
        for (InventoryItem item : allItems) {
            if (item.getQuantity() <= item.getProduct().getCriticalLevel()) {
                criticalItems.add(item);
            }
        }
        return criticalItems;
    }
}
